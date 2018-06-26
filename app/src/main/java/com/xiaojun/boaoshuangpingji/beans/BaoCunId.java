package com.xiaojun.boaoshuangpingji.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/6/26.
 */
@Entity
public class BaoCunId {
    @Id
    @NotNull
    private Long id;

    @Generated(hash = 1366340336)
    public BaoCunId(@NotNull Long id) {
        this.id = id;
    }

    @Generated(hash = 2067976164)
    public BaoCunId() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
