package lessons.turtleart;

import jlm.universe.turtles.Turtle;

class ScalaCircleTwoEntity extends Turtle {

	/* BEGIN TEMPLATE */
	override def run() {
		/* BEGIN SOLUTION */
		for (i <- 1 to 360) {
			forward(1.);
			right(1);
		}
		for (i <- 1 to 360) {
			forward(2);
			right(1);
		}
		/* END SOLUTION */
	}
	/* END TEMPLATE */
}
