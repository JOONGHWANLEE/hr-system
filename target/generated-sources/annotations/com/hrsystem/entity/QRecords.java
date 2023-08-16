package com.hrsystem.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecords is a Querydsl query type for Records
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecords extends EntityPathBase<Records> {

    private static final long serialVersionUID = 1819644031L;

    public static final QRecords records = new QRecords("records");

    public final DateTimePath<java.time.LocalDateTime> approvalDate = createDateTime("approvalDate", java.time.LocalDateTime.class);

    public final StringPath approvalNo = createString("approvalNo");

    public final DateTimePath<java.time.LocalDateTime> end = createDateTime("end", java.time.LocalDateTime.class);

    public final StringPath endTime = createString("endTime");

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public final DateTimePath<java.time.LocalDateTime> request = createDateTime("request", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> start = createDateTime("start", java.time.LocalDateTime.class);

    public final StringPath startTime = createString("startTime");

    public QRecords(String variable) {
        super(Records.class, forVariable(variable));
    }

    public QRecords(Path<? extends Records> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecords(PathMetadata metadata) {
        super(Records.class, metadata);
    }

}

