package lessons.welcome.methods.picture;

import java.awt.Color;

import jlm.universe.bugglequest.SimpleBuggle;
public class MethodsPictureEntity extends SimpleBuggle {

	/* BEGIN TEMPLATE */
	/* BEGIN SOLUTION */
	void mark() {
		brushDown();
		brushUp();
	}

	void makeV(Color c) {
		setBrushColor(c);
		forward();
		mark();

		forward();
		left();
		forward();
		mark();

		backward();
		right();
		forward();
		mark();

		forward();
		left();
	}



	public void run() {
		makeV(Color.YELLOW);
		makeV(Color.RED);
		makeV(Color.BLUE);
		makeV(Color.GREEN);
	}
	/* END SOLUTION */
	/* END TEMPLATE */
}
