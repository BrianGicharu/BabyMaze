
package mazebaby;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.event.*;


public class Keys{
	
	public class MoveEast extends AbstractAction{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1646214635977648276L;

		public void ActionPerformed(ActionEvent e){
			BabyMaze.BabyMoveEast();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	public class MoveWest extends AbstractAction{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -2705390203027130984L;

		public void ActionPerformed(ActionEvent e){
			BabyMaze.BabyMoveWest();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	public class MoveNorth extends AbstractAction{
		

		/**
		 * 
		 */
		private static final long serialVersionUID = -5204569685642059230L;

		public void ActionPerformed(ActionEvent e){
			BabyMaze.BabyMoveNorth();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
	public class MoveSouth extends AbstractAction{
		
		private static final long serialVersionUID = 1L;

		public void ActionPerformed(ActionEvent e){
			BabyMaze.BabyMoveSouth();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}