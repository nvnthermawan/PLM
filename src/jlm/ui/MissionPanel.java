package jlm.ui;

import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.StyleSheet;

import jlm.bugglequest.Game;
import jlm.event.GameListener;

import lessons.Exercise;

public class MissionPanel extends JEditorPane implements GameListener {

	private static final long serialVersionUID = 3891198543319748064L;
	private Game game;
	private Exercise currentExercise = null;
	
	public MissionPanel(Game game) {
		super("text/html", "");
		setEditable(false);
		// setPreferredSize(new Dimension(600,600));
		
		// load default css
		StyleSheet styles = ((HTMLDocument) getDocument()).getStyleSheet();
		try {
			styles.importStyleSheet(getClass().getResource("/lessons/screen.css"));			
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.game = game;
		this.game.addGameListener(this);
		currentExerciseHasChanged();
	}

	@Override
	public void currentExerciseHasChanged() {
		this.currentExercise = this.game.getCurrentLesson().getCurrentExercise();
		setText(this.currentExercise.getMission());
		setCaretPosition(0);
	}

	@Override
	public void currentLessonHasChanged() {
		// don't care
	}

	@Override
	public void lessonsChanged() {
		// don't care
	}
	
	@Override
	public void selectedWorldHasChanged() {
		// don't care
	}
	
	@Override
	public void selectedBuggleHasChanged() {
		// don't care
	}
	
	@Override
	public void selectedWorldWasUpdated() {
		// don't care
	}
}