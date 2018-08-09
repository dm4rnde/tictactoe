/*
Copyright 2018 dm4rnde (dm4rnde@protonmail.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package dm4rnde.tictactoe;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Setup logging for purpose of being able to enable and disable debugging
 * messages from one place.
 */
public class Loggr {

	public static final Logger LOGGER = Logger.getLogger(Consts.class.getPackage().getName());

	// here adjust the level, to get messages from only that level;
	// you can turn off/on logging, by setting level that is not being covered
	// by code; here, all simple debug messages would be on level
	// Level.FINE, and to see those debugging messages in console then set level (of
	// following variable) to Level.FINE as well; and to not see those messages you
	// could just set the level to Level.SEVERE, for example
	private static Level messageLevel = Level.FINE;
	// private static Level messageLevel = Level.SEVERE;

	static {
		LOGGER.setUseParentHandlers(false);
		LOGGER.setLevel(Level.ALL);
		// specify that console logging is required
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new LogFormatter());
		handler.setLevel(messageLevel);
		LOGGER.addHandler(handler);
	}

	// now do logging through this method instead of
	// System.out.println; call this method from all other
	// classes wherever debug message needs to be outputted
	public static void logFine(String msg) {
		LOGGER.log(Level.FINE, msg);
	}

}
