package SafetyNet.Alerts;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AlertsApplication.class)
class AlertsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void mainTest() {
		AlertsApplication.main(new String[] {});
	}
}
