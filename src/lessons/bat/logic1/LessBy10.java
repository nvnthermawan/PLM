/* automatically converted from the Nick Parlante's excellent exercising site http://javabat.com/ */

package lessons.bat.logic1;
import jlm.lesson.Lesson;
import jlm.universe.World;
import universe.bat.BatExercise;
import universe.bat.BatWorld;

public class LessBy10 extends BatExercise {
  public LessBy10(Lesson lesson) {
    super(lesson);
    
    World[] myWorlds = new BatWorld[14];
    myWorlds[0] = new BatWorld(VISIBLE, 1, 7, 11) ;
    myWorlds[1] = new BatWorld(VISIBLE, 1, 7, 10) ;
    myWorlds[2] = new BatWorld(VISIBLE, 11, 1, 7) ;
    myWorlds[3] = new BatWorld(INVISIBLE, 10, 7, 1) ;
    myWorlds[4] = new BatWorld(INVISIBLE, -10, 2, 2) ;
    myWorlds[5] = new BatWorld(INVISIBLE, 2, 11, 11) ;
    myWorlds[6] = new BatWorld(INVISIBLE, 3, 3, 30) ;
    myWorlds[7] = new BatWorld(INVISIBLE, 3, 3, 3) ;
    myWorlds[8] = new BatWorld(INVISIBLE, 10, 1, 11) ;
    myWorlds[9] = new BatWorld(INVISIBLE, 10, 11, 1) ;
    myWorlds[10] = new BatWorld(INVISIBLE, 10, 11, 2) ;
    myWorlds[11] = new BatWorld(INVISIBLE, 3, 30, 3) ;
    myWorlds[12] = new BatWorld(INVISIBLE, 2, 2, -8) ;
    myWorlds[13] = new BatWorld(INVISIBLE, 2, 8, 12) ;

    setup(myWorlds,"lessBy10");
  }

  /* BEGIN SKEL */
  public void run(World w) {
    BatWorld bw = (BatWorld) w;
    bw.result = lessBy10((Integer)w.getParameter(0), (Integer)w.getParameter(1), (Integer)w.getParameter(2));
  }
  /* END SKEL */

  /* BEGIN TEMPLATE */
boolean lessBy10(int a, int b, int c) {
  /* BEGIN SOLUTION */
  /* END SOLUTION */
}
  /* END TEMPLATE */
}