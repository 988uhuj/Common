/*
 Project:  scc
 Module:   scc-android-sdk
 File:     Executor.java
 Modifier: oznyang
 Modified: 2013-11-03 01:31

 Copyright (c) 2013 Wisorg Ltd. All Rights Reserved.

 Copying of this document or code and giving it to others and the
 use or communication of the contents thereof, are forbidden without
 expressed authority. Offenders are liable to the payment of damages.
 All rights reserved in the event of the grant of a invention patent
 or the registration of a utility model, design or code.
 */

package github.chenupt.common.util;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:oxsean@gmail.com">sean yang</a>
 * @version V1.0, 13-11-3
 */
public final class Exec {

    private static ExecutorService executor = Executors.newCachedThreadPool();

    public static void exe(Runnable runnable) {
        executor.execute(runnable);
    }

    public static <T> Future<T> exe(Callable<T> callable) {
        return executor.submit(callable);
    }

    public static <T> void exe(final Task<T> task) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());
                try {
                    final T result = task.call();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            task.onComplete(result);
                        }
                    });
                } catch (final Exception e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            task.onError(e);
                        }
                    });
                }
            }
        });
    }

    public static <T> void exe(final Task<T> task, final int timeoutSecond) {
        exe(task, timeoutSecond, TimeUnit.SECONDS);
    }

    public static <T> void exe(final Task<T> task, final int timeout, final TimeUnit timeUnit) {
        final Future<T> future = executor.submit(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return task.call();
            }
        });
        executor.submit(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());
                try {
                    final T result = future.get(timeout, timeUnit);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            task.onComplete(result);
                        }
                    });
                } catch (final Exception e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            task.onError(e);
                        }
                    });
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    public static <T> Future<T> exe(final Callable<T> callable, final Callback<T> callback) {
        return executor.submit(new Callable<T>() {
            @Override
            public T call() throws Exception {
                Handler handler = new Handler(Looper.getMainLooper());
                try {
                    final T result = callable.call();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (callback != null) {
                                callback.onComplete(result);
                            }
                        }
                    });
                    return result;
                } catch (final Exception e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (callback != null) {
                                callback.onError(e);
                            }
                        }
                    });
                    return null;
                }
            }
        });
    }

    public static void setExecutor(ExecutorService executor) {
        Exec.executor = executor;
    }

    public static ExecutorService getExecutor() {
        return executor;
    }

    private Exec() {
    }
}
