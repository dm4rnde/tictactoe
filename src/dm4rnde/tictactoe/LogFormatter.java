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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {

		StringBuilder line = new StringBuilder();

		line.append(new Date(record.getMillis())).append(" ").append(record.getLevel().getLocalizedName()).append(":\t\t")
				.append(formatMessage(record)).append("\n");

		if (record.getThrown() != null) {
			try {
				StringWriter sW = new StringWriter();
				PrintWriter pW = new PrintWriter(sW);
				record.getThrown().printStackTrace(pW);
				pW.close();
				line.append(sW.toString());
				sW.close();
			} catch (Exception ex) {
				// skip
			}
		}

		return line.toString();

	}

}