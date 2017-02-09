package com.leador.greendaodemo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xuwei on 2017/2/9.
 *
 * @Entity表示这个实体类一会会在数据库中生成对应的表，
 * @Id表示该字段是id，小伙伴们注意该字段的数据类型为包装类型Long，
 * @Property则表示该属性将作为表的一个字段，其中nameInDb看名字就知道这个属性在数据库中对应的数据名称。
 * 写完这些之后将项目进行编译，编译成功之后系统会帮助我们生成相应的构造方法和get/set方法，
 * 并且还会在我们的包下生成DaoMaster和DaoSession。那么这里常用的注解除了这几个之外，
 * 还有一个较常用的就是@Transient，该注解表示这个属性将不会作为数据表中的一个字段。
 * 另外还有一些比如@NotNull表示该字段不可以为空，@Unique表示该字段唯一。
 */
@Entity
public class User {
    @Id
    private Long id;
    @Property(nameInDb = "USERNAME")
    private String username;
    @Property(nameInDb = "NICKNAME")
    private String nickname;
    @Generated(hash = 523935516)
    public User(Long id, String username, String nickname) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
