package net.thisptr.dataset;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

/**
 * Fischer's iris data set. http://en.wikipedia.org/wiki/Iris_flower_data_set
 */
public class Iris {
	public static enum Species {
		Setosa,
		Versicolor,
		Virginica
	}

	public static class IrisEntry {
		public double sepalLength;
		public double sepalWidth;

		public double petalLength;
		public double petalWidth;

		public Species species;
	}

	private static final Pattern TAB = Pattern.compile("\t");

	public List<IrisEntry> getValues() {
		final List<IrisEntry> result = new ArrayList<IrisEntry>();

		try (final InputStreamReader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("dataset/iris.dat"), Charsets.UTF_8)) {
			final List<String> lines = CharStreams.readLines(reader);
			for (final String line : lines.subList(1, lines.size())) {
				final String[] values = TAB.split(line);

				final IrisEntry value = new IrisEntry();
				value.sepalLength = Double.valueOf(values[0]);
				value.sepalWidth = Double.valueOf(values[1]);
				value.petalLength = Double.valueOf(values[2]);
				value.petalWidth = Double.valueOf(values[3]);
				value.species = Species.valueOf(values[4]);
				result.add(value);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return result;
	}
}