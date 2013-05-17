package lessons.welcome.array.occurenceofvalue;

import java.util.Random;

import jlm.core.model.lesson.ExerciseTemplated;
import jlm.core.model.lesson.Lesson;
import jlm.universe.array.ArrayEntity;
import jlm.universe.array.ArrayWorld;

public class OccurrenceOfValue extends ExerciseTemplated {

	public OccurrenceOfValue(Lesson lesson) {
		super(lesson);
		
		ArrayWorld[] myWorlds = new ArrayWorld[2];
		myWorlds[0] = new ArrayWorld("Toy array", 10);
		myWorlds[0].setValues( new int[] { 2, -3, 1, 17, -13, 5, 3, 17, 9, 18 } );
		myWorlds[1] = new ArrayWorld("Bigger Array", 1);

		int[] tab = new int[30];
		Random r = new Random();
		for (int i=0; i<tab.length; i++) {
			tab[i] = r.nextInt(30);
		}
		myWorlds[1].setValues(tab);
		for (ArrayWorld w:myWorlds)
			new ArrayEntity("counter", w);

		
		setup(myWorlds);
	}
}



