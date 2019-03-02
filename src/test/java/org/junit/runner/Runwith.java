package org.junit.runner;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public @interface Runwith {

	Class<SpringJUnit4ClassRunner> value();

}
