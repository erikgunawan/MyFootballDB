package id.ergun.myfootballdb.utils.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class RxImmediateSchedulerRule : TestRule {

    /**
     * Get test scheduler for testing RxJava timeout.
     *
     * @return [TestScheduler] object for testing RxJava timeout.
     */
    val testScheduler: TestScheduler
        get() = TEST_SCHEDULER

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setIoSchedulerHandler { scheduler -> TEST_SCHEDULER }
                RxJavaPlugins.setComputationSchedulerHandler { scheduler -> TEST_SCHEDULER }
                RxJavaPlugins.setNewThreadSchedulerHandler { scheduler -> TEST_SCHEDULER }
                RxAndroidPlugins.setMainThreadSchedulerHandler { scheduler -> IMMEDIATE_SCHEDULER }
                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }

    companion object {

        private val TEST_SCHEDULER = TestScheduler()
        private val IMMEDIATE_SCHEDULER = object : Scheduler() {
            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                // Changing delay to 0 prevents StackOverflowErrors when scheduling with a delay.
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Scheduler.Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }
    }
}