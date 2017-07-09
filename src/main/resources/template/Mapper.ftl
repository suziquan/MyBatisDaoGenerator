<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapper.modelClassName}">

    <resultMap id="${mapper.modelClassName}Map" type="${mapper.modelClassName}">
        <id column="${mapper.idColumnName}" property="${mapper.idFieldName}"/>
    <#list mapper.commonColumnMapping as columnName,fieldName>
        <result column="${columnName}"  property="${fieldName}" />
    </#list>
        <result column="${mapper.createTimeColumnName}"  property="${mapper.createTimeFieldName}" />
        <result column="${mapper.updateTimeColumnName}"  property="${mapper.updateTimeFieldName}" />
    </resultMap>

    <sql id="tb">
        ${mapper.tableName}
    </sql>

    <sql id="cols_all">
        id,
        <include refid="cols_exclude_id"/>
    </sql>

    <sql id="cols_exclude_id">
    <#list mapper.commonColumnMapping as columnName,fieldName>
        ${wrapColumnName(columnName)},
    </#list>
        ${mapper.createTimeColumnName},
        ${mapper.updateTimeColumnName}
    </sql>

    <sql id="vals">
        ${r'#{'}${mapper.idFieldName}${r'}'},
    <#list mapper.commonColumnMapping as columnName,fieldName>
        ${r'#{'}${fieldName}${r'}'},
    </#list>
        now(),
        now()
    </sql>

    <sql id="updateCondition">
        <set>
        <#list mapper.commonColumnMapping as columnName,fieldName>
            <if test="${fieldName} != null">${wrapColumnName(columnName)} = ${r'#{'}${fieldName}${r'}'},</if>
        </#list>
            ${mapper.updateTimeColumnName} = now()
        </set>
    </sql>

    <sql id="queryCondition">
        <where>
            <if test="${mapper.idFieldName} != null">AND ${wrapColumnName(mapper.idColumnName)} = ${r'#{'}${mapper.idFieldName}${r'}'}</if>
        <#list mapper.commonColumnMapping as columnName,fieldName>
            <if test="${fieldName} != null">AND ${wrapColumnName(columnName)} = ${r'#{'}${fieldName}${r'}'}</if>
        </#list>
        </where>
    </sql>

    <sql id="custom_sort_type">
        <if test="sortType != null">
            <if test="sortType == 1">ASC</if>
            <if test="sortType == 2">DESC</if>
        </if>
    </sql>

    <sql id="custom_sort">
        <if test="sortBy != null">
            <if test="sortBy == '${mapper.idFieldName}'">ORDER BY ${wrapColumnName(mapper.idColumnName)}
                <include refid="custom_sort_type"/>
            </if>
        <#list mapper.commonColumnMapping as columnName,fieldName>
            <if test="sortBy == '${fieldName}'">ORDER BY ${wrapColumnName(columnName)}
                <include refid="custom_sort_type"/>
            </if>
        </#list>
            <if test="sortBy == '${mapper.createTimeFieldName}'">ORDER BY ${wrapColumnName(mapper.createTimeColumnName)}
                <include refid="custom_sort_type"/>
            </if>
            <if test="sortBy == '${mapper.updateTimeFieldName}'">ORDER BY ${wrapColumnName(mapper.updateTimeColumnName)}
                <include refid="custom_sort_type"/>
            </if>
        </if>
    </sql>

    <insert id="create" parameterType="${mapper.modelClassName}" keyProperty="id" useGeneratedKeys="true">
        INSERT INTO
        <include refid="tb"/>
        (<include refid="cols_exclude_id"/>)
        VALUES
        (<include refid="vals"/>)
    </insert>

    <insert id="creates" parameterType="list">
        INSERT INTO
        <include refid="tb"/>
        (<include refid="cols_exclude_id"/>)
        VALUES
        <foreach collection="list" item="i" index="index" separator=",">
            (
            <#list mapper.commonColumnMapping as columnName,fieldName>
            ${r'#{i.'}${fieldName}${r'}'},
            </#list>
            now(),
            now()
            )
        </foreach>
    </insert>

    <select id="load" parameterType="long" resultMap="${mapper.modelClassName}Map">
        SELECT
        <include refid="cols_all"/>
        FROM
        <include refid="tb"/>
        WHERE id = ${r'#{'}id${r'}'} LIMIT 1
    </select>

    <select id="loads" parameterType="list" resultMap="${mapper.modelClassName}Map">
        SELECT
        <include refid="cols_all"/>
        FROM
        <include refid="tb"/>
        WHERE id IN
        <foreach item="id" collection="list" open="(" separator="," close=")">
            ${r'#{'}id${r'}'}
        </foreach>
    </select>

    <delete id="delete" parameterType="long">
        DELETE FROM
        <include refid="tb"/>
        WHERE id = ${r'#{'}id${r'}'}
    </delete>

    <delete id="deletes" parameterType="list">
        DELETE FROM
        <include refid="tb"/>
        WHERE id IN
        <foreach item="id" collection="list" open="(" separator="," close=")">
            ${r'#{'}id${r'}'}
        </foreach>
    </delete>

    <update id="update" parameterType="${mapper.modelClassName}">
        UPDATE
        <include refid="tb"/>
        <include refid="updateCondition"/>
        WHERE ${mapper.idColumnName} = ${r'#{'}${mapper.idFieldName}${r'}'}
    </update>

    <select id="count" parameterType="map" resultType="long">
        SELECT COUNT(1)
        FROM
        <include refid="tb"/>
        <include refid="queryCondition"/>
    </select>

    <select id="paging" parameterType="map" resultMap="${mapper.modelClassName}Map">
        SELECT
        <include refid="cols_all"/>
        FROM
        <include refid="tb"/>
        <include refid="queryCondition"/>
        <include refid="custom_sort"/>
        LIMIT ${r'#{'}offset${r'}'}, ${r'#{'}limit${r'}'}
    </select>

</mapper>