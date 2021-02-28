package com.bfxy.base.rabbit.rabbittask.annotation;

import com.bfxy.base.rabbit.rabbittask.autoconfigure.JobParserAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Kylin Su
 * @date 2021/2/22 3:13 AM
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(JobParserAutoConfiguration.class)
public @interface EnableElasticJob {
}
