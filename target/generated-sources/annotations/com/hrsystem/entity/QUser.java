package com.hrsystem.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1446581586L;

    public static final QUser user = new QUser("user");

    public final StringPath dept = createString("dept");

    public final StringPath name = createString("name");

    public final StringPath no = createString("no");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath position = createString("position");

    public final EnumPath<com.hrsystem.constant.Role> role = createEnum("role", com.hrsystem.constant.Role.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

