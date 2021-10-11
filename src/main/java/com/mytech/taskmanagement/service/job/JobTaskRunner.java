package com.mytech.taskmanagement.service.job;

import com.google.common.collect.Lists;
import io.reactivex.Flowable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class JobTaskRunner {

    private int threadCt = Runtime.getRuntime().availableProcessors() + 1;
    private ExecutorService executorService = Executors.newFixedThreadPool(threadCt);
    private io.reactivex.Scheduler scheduler = io.reactivex.schedulers.Schedulers.from(executorService);

    @Autowired
    com.mytech.taskmanagement.dao.JobDao jobDao;



    boolean mapExample() {
        Flux<String> fluxColors = Flux.just("red", "green", "blue");
        fluxColors.log()
                .map(color -> color.charAt(0)).subscribe(System.out::println);

        return false;
    }

   private List<String> getJobTaskList() {
       List<String> siteModels = Lists.newArrayList("Kalamozoo", "Purss");
       List<String> siteToRun = new ArrayList<>();
       siteModels.stream().forEach(e -> {
          if(jobDao.createJob(e) != null){
              siteToRun.add(e);
          }else{
              log.info("Already Job inprogress");
          }
       });
         return siteToRun;
    }

    public void runConcurrentJob() {
        List siteModels = getJobTaskList();
        siteModels.stream().forEach(e -> {
            Flowable.fromCallable(() -> {
                TimeUnit.SECONDS.sleep(1);
                return e;
            }).doOnComplete(() -> {
                jobDao.updateTask((String) e);
                log.info("first Completable complete" + e);
            }).subscribeOn(scheduler)
                    .subscribe(s -> {
                        log.info("processing item on thread " + s);
                    });
        });
    }

    public void publishSubscribeExample() {

        Scheduler schedulerA = Schedulers.newParallel("Scheduler A");
        Scheduler schedulerB = Schedulers.newParallel("Scheduler B");
        Scheduler schedulerC = Schedulers.newParallel("Scheduler C");
        Flux.just(1)
                .map(i -> {
                    System.out.println("First map: " + Thread.currentThread().getName());
                    return i;
                })
                .subscribeOn(schedulerA)
                .map(i -> {
                    System.out.println("Second map: " + Thread.currentThread().getName());
                    return i;
                })
                .publishOn(schedulerB)
                .map(i -> {
                    System.out.println("Third map: " + Thread.currentThread().getName());
                    return i;
                })
                .subscribeOn(schedulerC)
                .map(i -> {
                    System.out.println("Fourth map: " + Thread.currentThread().getName());
                    return i;
                })
                .publishOn(schedulerA)
                .map(i -> {
                    System.out.println("Fifth map: " + Thread.currentThread().getName());
                    return i;
                })
                .blockLast();
    }



    public void onErrorExample() {
        Flux<String> fluxCalc = Flux.just(-1, 0, 1)
                .map(i -> "10 / " + i + " = " + (10 / i))
                .onErrorReturn(ArithmeticException.class, "Division by 0 not allowed");
        fluxCalc.subscribe(value -> System.out.println("Next: " + value),

                error -> System.err.println("Error: " + error));

    }


}
