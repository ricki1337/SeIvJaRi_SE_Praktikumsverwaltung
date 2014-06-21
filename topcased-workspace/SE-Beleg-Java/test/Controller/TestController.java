package Controller;

import static org.junit.Assert.*;

import org.junit.Test;

import Controller.Mock.MockController;
import Controller.Mock.MockModel;
import Models.Model;
import Views.View;

public class TestController {

	@Test(expected=IllegalArgumentException.class)
	public void testSetViewWithNull(){
		MockController controller = new MockController();
		controller.setView(null);
	}
	
	@Test
	public void testSetViewWithView(){
		MockController controller = new MockController();
		View view = new View(controller);
		controller.setModel(new MockModel());
		controller.setView(view);
		assertSame(view,controller.view);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetModelWithNull(){
		MockController controller = new MockController();
		controller.setModel(null);
	}
	
	@Test
	public void testSetModelWithModel(){
		MockController controller = new MockController();
		MockModel model = new MockModel();
		controller.setModel(model);
		assertSame(model, controller.model);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testGetDisplayedFrameWithEmptyView(){
		MockController controller = new MockController();
		controller.setModel(new MockModel());
		controller.getDisplayedFrame();
	}
	
	@Test
	public void testGetDisplayedFrameWithView(){
		MockController controller = new MockController();
		controller.setModel(new MockModel());
		View view = new View(controller);
		controller.setView(view);
		assertSame(view,controller.getDisplayedFrame());
	}
}
