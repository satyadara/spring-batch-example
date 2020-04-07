package com.satyadara.springbatch.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;

public interface BatchConfig<I, O> {
    Step step();

    Job job();
}
