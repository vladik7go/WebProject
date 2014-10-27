package test.by.epam.project;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class TestConnectionPoolRunner extends BlockJUnit4ClassRunner {
	private TestConnectionPoolRunListener runListener;

	public TestConnectionPoolRunner(Class<TestConnectionPoolRunListener> clazz)
			throws InitializationError {
		super(clazz);
		runListener = new TestConnectionPoolRunListener();
	}

	public void run(RunNotifier notifier) {
		notifier.addListener(runListener);
		super.run(notifier);
	}

}
