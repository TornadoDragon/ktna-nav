-- auto-generated definition
CREATE TABLE IF NOT EXISTS "sys_parameter"
(
    "param_code"     varchar_ignorecase(64)         NOT NULL,
    "param_name"     varchar_ignorecase(128)        NOT NULL,
    "param_value"    varchar_ignorecase(1000000000) NOT NULL,
    "param_describe" varchar_ignorecase(1000000000) NOT NULL,
    CONSTRAINT "pk_param_code"
        PRIMARY KEY ("param_code")
);

COMMENT ON TABLE "sys_parameter" IS '系统参数表';

COMMENT ON COLUMN "sys_parameter"."param_code" IS '参数编码';

COMMENT ON COLUMN "sys_parameter"."param_name" IS '参数名称';

COMMENT ON COLUMN "sys_parameter"."param_value" IS '参数值';

COMMENT ON COLUMN "sys_parameter"."param_describe" IS '参数描述';

CREATE TABLE IF NOT EXISTS "sys_nav_category"
(
    "id"    bigint(64)              NOT NULL
        PRIMARY KEY,
    "title" varchar_ignorecase(255) NOT NULL,
    "sort"  integer(32) DEFAULT 10  NOT NULL
);

COMMENT ON COLUMN "sys_nav_category"."id" IS '主键ID';

COMMENT ON COLUMN "sys_nav_category"."title" IS '分类标题';

COMMENT ON COLUMN "sys_nav_category"."sort" IS '排序码';


CREATE TABLE IF NOT EXISTS "sys_nav_item"
(
    "id"          bigint(64)               NOT NULL
        PRIMARY KEY,
    "category_id" bigint(64)               NOT NULL,
    "name"        varchar_ignorecase(255)  NOT NULL,
    "href"        varchar_ignorecase(1000) NOT NULL,
    "description" varchar_ignorecase(24),
    "logo"        binary large object(max),
    "sort"        integer(32) DEFAULT 10   NOT NULL
);

COMMENT ON COLUMN "sys_nav_item"."name" IS '导航卡片名';

COMMENT ON COLUMN "sys_nav_item"."href" IS '链接';

COMMENT ON COLUMN "sys_nav_item"."description" IS '描述';

COMMENT ON COLUMN "sys_nav_item"."logo" IS '图标';

COMMENT ON COLUMN "sys_nav_item"."sort" IS '排序码';

CREATE INDEX "idx_category_id"
    ON "sys_nav_item" ("category_id");

COMMENT ON INDEX "idx_category_id" IS '分类ID';

