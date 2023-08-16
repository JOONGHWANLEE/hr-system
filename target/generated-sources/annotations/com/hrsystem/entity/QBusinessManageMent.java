package com.hrsystem.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBusinessManageMent is a Querydsl query type for BusinessManageMent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBusinessManageMent extends EntityPathBase<BusinessManageMent> {

    private static final long serialVersionUID = 2068614630L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBusinessManageMent businessManageMent = new QBusinessManageMent("businessManageMent");

    public final EnumPath<com.hrsystem.constant.Approval> approval = createEnum("approval", com.hrsystem.constant.Approval.class);

    public final DateTimePath<java.time.LocalDateTime> approvalDate = createDateTime("approvalDate", java.time.LocalDateTime.class);

    public final StringPath approvalNo = createString("approvalNo");

    public final StringPath businessArea = createString("businessArea");

    public final StringPath businessReason = createString("businessReason");

    public final StringPath businessTitle = createString("businessTitle");

    public final EnumPath<com.hrsystem.constant.Division> division = createEnum("division", com.hrsystem.constant.Division.class);

    public final DateTimePath<java.time.LocalDateTime> end = createDateTime("end", java.time.LocalDateTime.class);

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public final DateTimePath<java.time.LocalDateTime> reportingDate = createDateTime("reportingDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> start = createDateTime("start", java.time.LocalDateTime.class);

    public final QUser user;

    public QBusinessManageMent(String variable) {
        this(BusinessManageMent.class, forVariable(variable), INITS);
    }

    public QBusinessManageMent(Path<? extends BusinessManageMent> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBusinessManageMent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBusinessManageMent(PathMetadata metadata, PathInits inits) {
        this(BusinessManageMent.class, metadata, inits);
    }

    public QBusinessManageMent(Class<? extends BusinessManageMent> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

