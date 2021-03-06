package in.leginto.studentstatus.client;


import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;

//import in.techno.testcell.client.TestTheCellTable.Contact;

public class StudentStatus implements EntryPoint {
	
	
	private static class Students
	{
		private final String sid;
		private final String name;
		private final String email;
		private final String mobile;
		
		public Students(String sid , String name, String email, String mobile)
		{
			this.sid = sid;
			this.name = name;
			this.email = email;
			this.mobile = mobile;
		}
		
		public String getSid()
		{
			return sid;
		}
		
		public String getName()
		{
			return name;
		}
		public String getEmail()
		{
			return email;
		}
		
		public String getMobile()
		{
			return mobile;
		}
		
		
		
	}
	
	
	
	//private static final List<Students> STUDENTS = Arrays.asList();
			
	@Override
	public void onModuleLoad() {
		
		final CellTable<Students> table = new CellTable<Students>();
		
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
		
		
		// create 4 columns 
		TextColumn<Students> sidColumn = new TextColumn<StudentStatus.Students>() {

			@Override
			public String getValue(Students object) {
				
				return object.getSid();
			}
		};
		
		TextColumn<Students> nameColumn = new TextColumn<StudentStatus.Students>() {

			@Override
			public String getValue(Students object) {
				
				return object.getName();
			}
		};
		
		TextColumn<Students> emailColumn = new TextColumn<StudentStatus.Students>() {

			@Override
			public String getValue(Students object) {
				
				return object.getEmail();
			}
		};
		
		TextColumn<Students> mobileColumn = new TextColumn<StudentStatus.Students>() {

			@Override
			public String getValue(Students object) {
				
				return object.getMobile();
			}
		};
		
		table.addColumn(sidColumn,"Student ID");
		table.addColumn(nameColumn, "Name");
		table.addColumn(emailColumn, "E-mail");
		table.addColumn(mobileColumn, "Mobile Number");
		
		
		
		
		final ListDataProvider<Students> dataProvider = new ListDataProvider<Students>();
		
		dataProvider.addDataDisplay(table);
		
		
		final List<Students> list = dataProvider.getList();
		
		
		final RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET,"studentnames.json");
		
		
		try {
			requestBuilder.sendRequest(null, new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					
					
					JSONArray jsonArray = (JSONArray) JSONParser.parseStrict(response.getText());
					
					for(int i=0; i<jsonArray.size(); i++)
					{
						JSONObject jsonObject = (JSONObject) jsonArray.get(i);
						list.add(new Students(
								jsonObject.get("sid").toString().replace("\"", ""), 
								jsonObject.get("sname").toString().replace("\"", ""), 
								jsonObject.get("semail").toString().replace("\"", ""), 
								jsonObject.get("smobile").toString().replace("\"", "")));
					}
					
					
					
				}
				
				@Override
				public void onError(Request request, Throwable exception) {
					
					
				}
			});
		} catch (RequestException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// dialog layout for add student
		
		final Grid addStuGrid = new Grid(3, 2);
		
		
		HorizontalPanel horizPan4 = new HorizontalPanel();
		
		
		// text boxes
		
		final TextBox nameText = new TextBox();
		final TextBox emailText = new TextBox();
		final TextBox mobileText = new TextBox();
		

		// add student click events
		
		nameText.addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				// TODO Auto-generated method stub
				if(nameText.getText().length()==0)
				{
					nameText.setText("Enter Name");
					nameText.getElement().getStyle().setColor("#aaa");
					
				}
				else if(nameText.getText().equals("Enter Name"))
				{
					nameText.setText("");
					nameText.getElement().getStyle().setColor("#000");
					
				}
				
			}
		});
		
		nameText.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				
				if(nameText.getText().length()==0)
				{
					nameText.setText("Enter Name");
					nameText.getElement().getStyle().setColor("#aaa");
				}
			}
		});
		
		//------------------
		
		
		emailText.addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				// TODO Auto-generated method stub
				if(emailText.getText().length()==0)
				{
					emailText.setText("email@example.com");
					emailText.getElement().getStyle().setColor("#aaa");
					
				}
				else if(emailText.getText().equals("email@example.com"))
				{
					emailText.setText("");
					emailText.getElement().getStyle().setColor("#000");
					
				}
				
			}
		});
		
		
		emailText.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				
				if(emailText.getText().length()==0)
				{
					emailText.setText("email@example.com");
					emailText.getElement().getStyle().setColor("#aaa");
					
				}
				
			}
		});
		
		
		
		//-----------------
		
		mobileText.addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				// TODO Auto-generated method stub
				if(mobileText.getText().length()==0)
				{
					mobileText.setText("9999999999");
					mobileText.getElement().getStyle().setColor("#aaa");
					
				}
				else if(mobileText.getText().equals("9999999999"))
				{
					mobileText.setText("");
					mobileText.getElement().getStyle().setColor("#000");
					
				}
				
			}
		});
		
		mobileText.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				
				if(mobileText.getText().length()==0)
				{
					mobileText.setText("9999999999");
					mobileText.getElement().getStyle().setColor("#aaa");
					
				}
			}
		});
		
		
		
		
		
		// save button
		Button saveButton = new Button("Save");
		Button cancelButton = new Button("Cancel");
		final VerticalPanel vertiPan = new VerticalPanel();
		
		
		addStuGrid.setText(0, 0, "Name:");
		addStuGrid.setText(1, 0, "Email Id:");
		addStuGrid.setText(2, 0, "Mobile No:");
		
		addStuGrid.setWidget(0, 1, nameText);
		addStuGrid.setWidget(1, 1, emailText);
		addStuGrid.setWidget(2, 1, mobileText);
		
		
		
		
		horizPan4.add(saveButton);
		horizPan4.add(cancelButton);
		
		
		vertiPan.add(addStuGrid);
		vertiPan.add(horizPan4);
		
		
		
		
		// add student command
		
		
		final DialogBox addStuDialog = new DialogBox();
		
		
		Command addStudent = new Command() {
			
			@Override
			public void execute() {
				
				//Window.alert("Student added");
				
				
				
				// setting values
				
				nameText.setText("Enter Name");
				emailText.setText("email@example.com");
				mobileText.setText("9999999999");
				
				nameText.getElement().getStyle().setColor("#aaa");
				emailText.getElement().getStyle().setColor("#aaa");
				mobileText.getElement().getStyle().setColor("#aaa");
				
				
				//creating a dialog box
				
				addStuDialog.setText("Adding a new Student");
				addStuDialog.setGlassEnabled(true);
				addStuDialog.center();
				addStuDialog.add(vertiPan);
				addStuDialog.center();
				addStuDialog.show();
				
				
				
			}
		};
		
		
		// save button logic
		saveButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				//addStuDialog.hide();
				
				// verify for valid data
				
				
				String errorMsg = "Possible Mistakes:\n";
				
				boolean validData = true;
				
				ValidInputCheck validInput = new ValidInputCheck();
				
				if(! validInput.isValidName(nameText.getText().toString()))
				{
					errorMsg = errorMsg.concat("Name: Special characters not allowed\n");
					validData = false;
					nameText.getElement().getStyle().setColor("#f00");
					nameText.addKeyDownHandler(new KeyDownHandler() {
						
						@Override
						public void onKeyDown(KeyDownEvent event) {
							// TODO Auto-generated method stub
							nameText.getElement().getStyle().setColor("#000");
						}
					});
				}
				
				if(! validInput.isValidEmail(emailText.getText().toString()))
				{
					errorMsg = errorMsg.concat("Email: Only _  . allowed and check domain name also\n");
					validData = false;
					emailText.getElement().getStyle().setColor("#f00");
					emailText.addKeyDownHandler(new KeyDownHandler() {
						
						@Override
						public void onKeyDown(KeyDownEvent event) {
							// TODO Auto-generated method stub
							emailText.getElement().getStyle().setColor("#000");
						}
					});
				}
				
				if(! validInput.isValidMobile(mobileText.getText().toString()))
				{
					errorMsg = errorMsg.concat("Mobile: First digit must be 9, number of digits should be 10 only\n");
					validData = false;
					mobileText.getElement().getStyle().setColor("#f00");
					mobileText.addKeyDownHandler(new KeyDownHandler() {
						
						@Override
						public void onKeyDown(KeyDownEvent event) {
							// TODO Auto-generated method stub
							mobileText.getElement().getStyle().setColor("#000");
						}
					});
				}
				
				
				//Window.alert(""+list.size());
				
				
				// logic to add (name, email, mobile)to the existing record
				
				ConvertAlphabets alpha = new ConvertAlphabets();
				
				if(nameText.getText().toString() != "Enter Name" && 
						emailText.getText().toString() != "email@example.com" && 
						mobileText.getText().toString()!= "9999999999" && validData)
				{
					addStuDialog.hide();
					String newSid = ""+(Integer.parseInt(list.get(list.size()-1).getSid().substring(3)) +1);
					if(list.size()==0)
					{
						Window.alert("null");
						//newSid="001";
					}
						
					while(newSid.length()<3)
					{
						newSid = "0"+newSid;
					}
					
					//Window.alert("svae region");
					
					list.add(new Students("STD"+newSid ,alpha.convertSentenceCase(nameText.getText().toString()), 
							emailText.getText().toString(), 
							mobileText.getText().toString()));
					
					dataProvider.refresh();
					
				}
				
				if(! errorMsg.equals("Possible Mistakes:\n"))
				{
					Window.alert(errorMsg);
				}
				
			}
		});
		
		
		final DialogBox viewStuDialog = new DialogBox();
		
		viewStuDialog.setText("View Student Details");
		
		final Grid viewStuGrid = new Grid(5,2);
		
		final Button closeButton = new Button("Close");
		
		final VerticalPanel verticalPanel2 = new VerticalPanel(); 
		
		
		// to get selection
		
		final SingleSelectionModel<Students> selectionModel = new SingleSelectionModel<Students>();
	    table.setSelectionModel(selectionModel);
	    
	    
		
		// view student command
		Command viewStudent = new Command() {
			
			@Override
			public void execute() {
				
				
		    	Students selected = selectionModel.getSelectedObject();
		        if (selected != null) {
		        	
		        	viewStuGrid.setText(0, 0, "Student Id:");
					viewStuGrid.setText(1, 0, "Name:");
					viewStuGrid.setText(2, 0, "E-mail:");
					viewStuGrid.setText(3, 0, "Mobile No:");
					
					viewStuGrid.setText(0, 1, selected.getSid().toString());
					viewStuGrid.setText(1, 1, selected.getName().toString());
					viewStuGrid.setText(2, 1, selected.getEmail().toString());
					viewStuGrid.setText(3, 1, "+91-"+selected.getMobile().toString());
					
					
					verticalPanel2.add(viewStuGrid);
					verticalPanel2.add(closeButton);				
					
					viewStuDialog.setGlassEnabled(true);
					viewStuDialog.center();
					viewStuDialog.add(verticalPanel2);
					
					
		        }
		        else
		        {
		        	Window.alert("Please Select a student first");
		        }
				
			}
		};
		
		
		closeButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				
				selectionModel.clear();
				table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
				viewStuDialog.hide();
				
			}
		});
		
		
		Command removeStudent = new Command() {
			
			@Override
			public void execute() {
				
				boolean selectStu = false;
				
				for(int i=0; i< list.size(); i++)
				{
					if(selectionModel.isSelected(list.get(i)))
					{
						list.remove(i);
						dataProvider.refresh();
						selectStu = true;
						selectionModel.clear();
						break;
						
					}
				}
				
				
				
				
				if(! selectStu)
				{
					Window.alert("Please Select a student first");
				}
				
			}
		};
		
		
		// created menu bar
		final MenuBar mainMenu = new MenuBar();
		
		mainMenu.addItem("Add Student",addStudent);
		mainMenu.addItem("View Student", viewStudent);
		mainMenu.addItem("Remove Student", removeStudent);
		
		
		
		cancelButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				addStuDialog.hide();
				
			}
		});
		
		
		// implementation link
		Anchor link = new Anchor();
		link.setText("Implementation");
		
		
		link.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				Window.open("https://github.com/leginto/StudentDetails", null, null);
				
			}
		});
		
		
		RootPanel.get().add(mainMenu);
		RootPanel.get().add(table);
		RootPanel.get().add(link);
		
	}
}
