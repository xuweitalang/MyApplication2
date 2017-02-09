package com.leador.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.leador.greendaodemo.gen.DaoMaster;
import com.leador.greendaodemo.gen.DaoSession;
import com.leador.greendaodemo.gen.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvName;
    private Button btnAdd;
    private Button btnQuery;
    private Button btnUpdate;
    private Button btnDel;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDB();
        User user = new User(null,"xw","www");
        userDao.insert(user);
    }

    private void initView() {
        tvName = (TextView) findViewById(R.id.tvName);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnQuery = (Button) findViewById(R.id.btnquery);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDel = (Button) findViewById(R.id.btnDel);
        btnAdd.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDel.setOnClickListener(this);
    }

    /**
     * 初始化数据库
     * <p>
     * “notes-db”是我们自定义的数据库名字，因为我们之前创建了一个Entity叫做User，
     * 所以greenDAO自动帮我们生成的UserDao，拿到了这个UserDao，我们就可以操作User这张表了。
     * 一个DaoMaster就代表着一个数据库的连接；DaoSession可以让我们使用一些Entity的基本操作和获取Dao操作类，
     * DaoSession可以创建多个，每一个都是属于同一个数据库连接的。
     */
    private void initDB() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "note-db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
    }

    /**
     * 往数据库中添加数据
     */
    private void add2DB() {
        User user = new User(null,"xuwei","xt");
        userDao.insert(user);
    }

    /**
     * 查询数据库
     * @return 返回查询到的数据列表
     */
    private List<User> queryData() {
        return userDao.queryBuilder().build().list();
    }

    /**
     * 更新数据库中数据
     * @param oldUserName 老数据
     * @param newUserName 新数据
     */
    private void updateData(String oldUserName, String newUserName) {
        List<User> list = userDao.queryBuilder().where(UserDao.Properties.Username.eq(oldUserName)).build().list();

        if (list != null && list.size()>0) {
            list.get(0).setUsername(newUserName);
            Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"修改失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                add2DB();
                break;
            case R.id.btnquery:
                StringBuilder sb = new StringBuilder();
                List<User> userList = queryData();
                for(User user : userList) {
                    sb.append(user.getUsername());
                }
                tvName.setText(sb);
                break;
            case R.id.btnUpdate:
                updateData("xuwei","xt");
                break;
            case R.id.btnDel:
                delData("www");
        }
    }

    private void delData(String nickName) {
        List<User> list = userDao.queryBuilder().where(UserDao.Properties.Nickname.eq(nickName)).build().list();
        if (list != null && list.size()>0) {
            userDao.deleteByKey(list.get(0).getId());
            Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"修改失败",Toast.LENGTH_SHORT).show();
        }
    }
}
