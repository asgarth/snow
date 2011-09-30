package example.org.snow.util;

import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class CaptureScreenshotOnFailure implements MethodRule {

	public final Statement apply( final Statement base, final FrameworkMethod method, Object target ) {
		final String filename = "./" + method.getMethod().getDeclaringClass().getCanonicalName() + "."
				+ method.getName() + ".png";

		return new Statement() {

			@Override
			public void evaluate() throws Throwable {
				try {
					base.evaluate();
				} catch( Throwable onHold ) {
					SWTUtils.captureScreenshot( filename );
					throw onHold;
				}
			}
		};
	}

}
