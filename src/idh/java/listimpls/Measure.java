package idh.java.listimpls;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Measure<T extends List<Integer>> {
	Random random = new Random(0);
	int n = (int) Math.pow(10.0, 7.0);
	int m = 100;
	OutputStream os = OutputStream.nullOutputStream();
	OutputStreamWriter osw = new OutputStreamWriter(os);
	long start;

	public Measure() {
		System.out.println("This list contains n = " + n + " elements.");
	}

	public void filling(T list) {
		start = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			list.add(random.nextInt());
		}
		System.out.println("- fill with " + n + " random elements: " + (System.currentTimeMillis() - start) + "ms.");
	}

	public void randomAccess(T list) throws IOException {
		start = System.currentTimeMillis();
		for (int i = 0; i < m; i++)
			osw.write(list.get(random.nextInt(n)));
		System.out.println("- access " + m + " random elements: " + (System.currentTimeMillis() - start) + "ms.");
	}

	public void randomRemoval(T list) {
		start = System.currentTimeMillis();
		for (int i = 0; i < m; i++)
			list.remove(random.nextInt(n - i));
		System.out.println("- remove " + m + " random positions: " + (System.currentTimeMillis() - start) + "ms.");
	}

	public void removeInIteration(T list) {
		start = System.currentTimeMillis();
		Iterator<Integer> iter = list.listIterator();
		int removed = 0;
		double percentage = 0.0001;
		while (iter.hasNext()) {
			iter.next();
			if (random.nextDouble() < percentage) {
				iter.remove();
				removed++;
			}
		}
		System.out.println("- remove random " + removed + " positions (" + percentage * 100 + "%) via iterator: "
				+ (System.currentTimeMillis() - start) + "ms.");

	}

	public static void main(String[] args) throws IOException {
		List<Integer> list = new LinkedList<Integer>();

		System.out.println("Timing for " + list.getClass().getName());
		Measure<List<Integer>> m = new Measure<List<Integer>>();
		m.filling(list);
		m.randomAccess(list);
		m.randomRemoval(list);
		m.removeInIteration(list);

		list = null;
		System.gc();

		list = new ArrayList<Integer>();
		System.out.println("Timing for " + list.getClass().getName());
		m = new Measure<List<Integer>>();
		m.filling(list);
		m.randomAccess(list);
		m.randomRemoval(list);
		m.removeInIteration(list);
	}
}
