package gr.mythologia;

import android.webkit.WebSettings;
import android.webkit.WebView;

import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "gr.mythologia", "gr.mythologia.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "gr.mythologia", "gr.mythologia.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "gr.mythologia.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (main) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public static anywheresoftware.b4a.objects.collections.List _mythologydata = null;
public static anywheresoftware.b4a.objects.collections.List _mythologydatael = null;
public static anywheresoftware.b4a.objects.collections.List _mythologydataen = null;
public static anywheresoftware.b4a.objects.collections.Map _imagemap = null;
public static String _currentlanguage = "";
public static boolean _mainuiready = false;
public anywheresoftware.b4a.objects.ScrollViewWrapper _sventries = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _pnlmain = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _txtsearch = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _pnldetail = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lbldetailname = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lbldetaildesc = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _svdetail = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _btnback = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _lbllang = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _pnlsplash = null;
public anywheresoftware.b4a.objects.Timer _tmrsearch = null;
public b4a.example.dateutils _dateutils = null;
public gr.mythologia.starter _starter = null;
public gr.mythologia.xuiviewsutils _xuiviewsutils = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static class _linkmatch{
public boolean IsInitialized;
public int start;
public int endIdx;
public anywheresoftware.b4a.objects.collections.Map entry;
public void Initialize() {
IsInitialized = true;
start = 0;
endIdx = 0;
entry = new anywheresoftware.b4a.objects.collections.Map();
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static void  _activity_create(boolean _firsttime) throws Exception{
ResumableSub_Activity_Create rsub = new ResumableSub_Activity_Create(null,_firsttime);
rsub.resume(processBA, null);
}
public static class ResumableSub_Activity_Create extends BA.ResumableSub {
public ResumableSub_Activity_Create(gr.mythologia.main parent,boolean _firsttime) {
this.parent = parent;
this._firsttime = _firsttime;
}
gr.mythologia.main parent;
boolean _firsttime;
anywheresoftware.b4a.objects.ScrollViewWrapper _sv = null;
anywheresoftware.b4a.objects.PanelWrapper _pheader = null;
anywheresoftware.b4a.objects.B4XViewWrapper _bheader = null;
anywheresoftware.b4a.objects.EditTextWrapper _et = null;
anywheresoftware.b4a.objects.B4XViewWrapper _bet = null;
anywheresoftware.b4a.objects.EditTextWrapper _et1 = null;
anywheresoftware.b4a.objects.LabelWrapper _llang = null;
anywheresoftware.b4a.objects.LabelWrapper _lclear = null;
anywheresoftware.b4a.objects.B4XViewWrapper _bclear = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 48;BA.debugLine="Log(\"Activity_Create Start\")";
anywheresoftware.b4a.keywords.Common.LogImpl("4131073","Activity_Create Start",0);
 //BA.debugLineNum = 49;BA.debugLine="MainUIReady = False";
parent._mainuiready = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 52;BA.debugLine="Activity.RemoveAllViews";
parent.mostCurrent._activity.RemoveAllViews();
 //BA.debugLineNum = 53;BA.debugLine="Activity.Color = 0xFF111128";
parent.mostCurrent._activity.setColor(((int)0xff111128));
 //BA.debugLineNum = 56;BA.debugLine="CreateSplashScreen";
_createsplashscreen();
 //BA.debugLineNum = 59;BA.debugLine="If FirstTime Then";
if (true) break;

case 1:
//if
this.state = 4;
if (_firsttime) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 60;BA.debugLine="LoadData";
_loaddata();
 if (true) break;

case 4:
//C
this.state = 5;
;
 //BA.debugLineNum = 65;BA.debugLine="Dim sv As ScrollView : sv.Initialize(0)";
_sv = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 65;BA.debugLine="Dim sv As ScrollView : sv.Initialize(0)";
_sv.Initialize(mostCurrent.activityBA,(int) (0));
 //BA.debugLineNum = 66;BA.debugLine="svEntries = sv";
parent.mostCurrent._sventries = _sv;
 //BA.debugLineNum = 67;BA.debugLine="Activity.AddView(svEntries, 0, 60dip, 100%x, 100%";
parent.mostCurrent._activity.AddView((android.view.View)(parent.mostCurrent._sventries.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60))));
 //BA.debugLineNum = 68;BA.debugLine="svEntries.Visible = False ' Hide until splash end";
parent.mostCurrent._sventries.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 71;BA.debugLine="Dim pHeader As Panel : pHeader.Initialize(\"\")";
_pheader = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 71;BA.debugLine="Dim pHeader As Panel : pHeader.Initialize(\"\")";
_pheader.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 72;BA.debugLine="Dim bHeader As B4XView = pHeader";
_bheader = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bheader = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_pheader.getObject()));
 //BA.debugLineNum = 73;BA.debugLine="Activity.AddView(bHeader, 0, 0, 100%x, 60dip)";
parent.mostCurrent._activity.AddView((android.view.View)(_bheader.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
 //BA.debugLineNum = 74;BA.debugLine="bHeader.Color = 0xFF242445";
_bheader.setColor(((int)0xff242445));
 //BA.debugLineNum = 75;BA.debugLine="bHeader.Visible = False";
_bheader.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 78;BA.debugLine="Dim et As EditText : et.Initialize(\"txtSearch\")";
_et = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 78;BA.debugLine="Dim et As EditText : et.Initialize(\"txtSearch\")";
_et.Initialize(mostCurrent.activityBA,"txtSearch");
 //BA.debugLineNum = 79;BA.debugLine="et.Hint = \"Search myths...\"";
_et.setHint("Search myths...");
 //BA.debugLineNum = 80;BA.debugLine="et.HintColor = 0xFFA89878";
_et.setHintColor(((int)0xffa89878));
 //BA.debugLineNum = 81;BA.debugLine="et.TextColor = xui.Color_White";
_et.setTextColor(parent._xui.Color_White);
 //BA.debugLineNum = 82;BA.debugLine="et.TextSize = 16";
_et.setTextSize((float) (16));
 //BA.debugLineNum = 83;BA.debugLine="et.Color = 0xFF1A1A35";
_et.setColor(((int)0xff1a1a35));
 //BA.debugLineNum = 84;BA.debugLine="txtSearch = et";
parent.mostCurrent._txtsearch = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_et.getObject()));
 //BA.debugLineNum = 85;BA.debugLine="Dim bET As B4XView = et";
_bet = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bet = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_et.getObject()));
 //BA.debugLineNum = 86;BA.debugLine="bET.SetTextAlignment(\"CENTER\", \"LEFT\")";
_bet.SetTextAlignment("CENTER","LEFT");
 //BA.debugLineNum = 87;BA.debugLine="bHeader.AddView(txtSearch, 10dip, 5dip, 100%x - 1";
_bheader.AddView((android.view.View)(parent.mostCurrent._txtsearch.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 90;BA.debugLine="If CurrentLanguage = \"el\" Then";
if (true) break;

case 5:
//if
this.state = 8;
if ((parent._currentlanguage).equals("el")) { 
this.state = 7;
}if (true) break;

case 7:
//C
this.state = 8;
 //BA.debugLineNum = 91;BA.debugLine="Dim et1 As EditText = txtSearch";
_et1 = new anywheresoftware.b4a.objects.EditTextWrapper();
_et1 = (anywheresoftware.b4a.objects.EditTextWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.EditTextWrapper(), (android.widget.EditText)(parent.mostCurrent._txtsearch.getObject()));
 //BA.debugLineNum = 92;BA.debugLine="et1.InputType = Bit.Or(1, 4096) ' TYPE_CLASS_TEX";
_et1.setInputType(anywheresoftware.b4a.keywords.Common.Bit.Or((int) (1),(int) (4096)));
 if (true) break;

case 8:
//C
this.state = -1;
;
 //BA.debugLineNum = 96;BA.debugLine="Dim lLang As Label : lLang.Initialize(\"lblLang\")";
_llang = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 96;BA.debugLine="Dim lLang As Label : lLang.Initialize(\"lblLang\")";
_llang.Initialize(mostCurrent.activityBA,"lblLang");
 //BA.debugLineNum = 97;BA.debugLine="lblLang = lLang";
parent.mostCurrent._lbllang = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_llang.getObject()));
 //BA.debugLineNum = 98;BA.debugLine="lblLang.Text = \"ΕΛ\"";
parent.mostCurrent._lbllang.setText(BA.ObjectToCharSequence("ΕΛ"));
 //BA.debugLineNum = 99;BA.debugLine="lblLang.TextColor = 0xFFEDD060";
parent.mostCurrent._lbllang.setTextColor(((int)0xffedd060));
 //BA.debugLineNum = 100;BA.debugLine="lblLang.TextSize = 18";
parent.mostCurrent._lbllang.setTextSize((float) (18));
 //BA.debugLineNum = 101;BA.debugLine="lblLang.Font = xui.CreateDefaultBoldFont(18)";
parent.mostCurrent._lbllang.setFont(parent._xui.CreateDefaultBoldFont((float) (18)));
 //BA.debugLineNum = 102;BA.debugLine="lblLang.SetTextAlignment(\"CENTER\", \"CENTER\")";
parent.mostCurrent._lbllang.SetTextAlignment("CENTER","CENTER");
 //BA.debugLineNum = 103;BA.debugLine="lblLang.Color = 0xFF1A1A35";
parent.mostCurrent._lbllang.setColor(((int)0xff1a1a35));
 //BA.debugLineNum = 104;BA.debugLine="bHeader.AddView(lblLang, 100%x - 65dip, 5dip, 55d";
_bheader.AddView((android.view.View)(parent.mostCurrent._lbllang.getObject()),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (65))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 107;BA.debugLine="Dim lClear As Label : lClear.Initialize(\"lblClear";
_lclear = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 107;BA.debugLine="Dim lClear As Label : lClear.Initialize(\"lblClear";
_lclear.Initialize(mostCurrent.activityBA,"lblClear");
 //BA.debugLineNum = 108;BA.debugLine="Dim bClear As B4XView = lClear";
_bclear = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bclear = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lclear.getObject()));
 //BA.debugLineNum = 109;BA.debugLine="bClear.Text = \"✕\"";
_bclear.setText(BA.ObjectToCharSequence("✕"));
 //BA.debugLineNum = 110;BA.debugLine="bClear.TextColor = 0xFFA89878";
_bclear.setTextColor(((int)0xffa89878));
 //BA.debugLineNum = 111;BA.debugLine="bClear.TextSize = 18";
_bclear.setTextSize((float) (18));
 //BA.debugLineNum = 112;BA.debugLine="bClear.SetTextAlignment(\"CENTER\", \"CENTER\")";
_bclear.SetTextAlignment("CENTER","CENTER");
 //BA.debugLineNum = 113;BA.debugLine="bHeader.AddView(bClear, 100%x - 110dip, 5dip, 40d";
_bheader.AddView((android.view.View)(_bclear.getObject()),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (110))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 116;BA.debugLine="tmrSearch.Initialize(\"tmrSearch\", 300)";
parent.mostCurrent._tmrsearch.Initialize(processBA,"tmrSearch",(long) (300));
 //BA.debugLineNum = 117;BA.debugLine="tmrSearch.Enabled = False";
parent.mostCurrent._tmrsearch.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 120;BA.debugLine="CreateDetailPanel";
_createdetailpanel();
 //BA.debugLineNum = 123;BA.debugLine="MainUIReady = True";
parent._mainuiready = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 124;BA.debugLine="FillList(\"\")";
_filllist("");
 //BA.debugLineNum = 127;BA.debugLine="Sleep(2500) ' Let user enjoy the splash";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (2500));
this.state = 9;
return;
case 9:
//C
this.state = -1;
;
 //BA.debugLineNum = 128;BA.debugLine="pnlSplash.SetLayoutAnimated(500, -100%x, 0, 100%x";
parent.mostCurrent._pnlsplash.SetLayoutAnimated((int) (500),(int) (-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 129;BA.debugLine="svEntries.Visible = True";
parent.mostCurrent._sventries.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 130;BA.debugLine="bHeader.Visible = True";
_bheader.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 131;BA.debugLine="lblLang.BringToFront";
parent.mostCurrent._lbllang.BringToFront();
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 646;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 647;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 648;BA.debugLine="If pnlDetail.Visible Then";
if (mostCurrent._pnldetail.getVisible()) { 
 //BA.debugLineNum = 649;BA.debugLine="pnlDetail.Visible = False";
mostCurrent._pnldetail.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 650;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 654;BA.debugLine="ConfirmExit";
_confirmexit();
 //BA.debugLineNum = 655;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 657;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 658;BA.debugLine="End Sub";
return false;
}
public static String  _btnback_click() throws Exception{
 //BA.debugLineNum = 251;BA.debugLine="Sub btnBack_Click";
 //BA.debugLineNum = 252;BA.debugLine="pnlDetail.Visible = False";
mostCurrent._pnldetail.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 253;BA.debugLine="End Sub";
return "";
}
public static String  _cleanjs(String _content,String _varname) throws Exception{
int _idx1 = 0;
int _idx2 = 0;
int _startidx = 0;
 //BA.debugLineNum = 298;BA.debugLine="Sub CleanJS(Content As String, VarName As String)";
 //BA.debugLineNum = 300;BA.debugLine="Dim idx1 As Int = Content.IndexOf(\"[\")";
_idx1 = _content.indexOf("[");
 //BA.debugLineNum = 301;BA.debugLine="Dim idx2 As Int = Content.IndexOf(\"{\")";
_idx2 = _content.indexOf("{");
 //BA.debugLineNum = 303;BA.debugLine="Dim startIdx As Int = -1";
_startidx = (int) (-1);
 //BA.debugLineNum = 304;BA.debugLine="If idx1 > -1 And idx2 > -1 Then";
if (_idx1>-1 && _idx2>-1) { 
 //BA.debugLineNum = 305;BA.debugLine="startIdx = Min(idx1, idx2)";
_startidx = (int) (anywheresoftware.b4a.keywords.Common.Min(_idx1,_idx2));
 }else if(_idx1>-1) { 
 //BA.debugLineNum = 307;BA.debugLine="startIdx = idx1";
_startidx = _idx1;
 }else {
 //BA.debugLineNum = 309;BA.debugLine="startIdx = idx2";
_startidx = _idx2;
 };
 //BA.debugLineNum = 312;BA.debugLine="If startIdx > -1 Then";
if (_startidx>-1) { 
 //BA.debugLineNum = 313;BA.debugLine="Content = Content.SubString(startIdx)";
_content = _content.substring(_startidx);
 };
 //BA.debugLineNum = 317;BA.debugLine="Content = Content.Trim";
_content = _content.trim();
 //BA.debugLineNum = 318;BA.debugLine="If Content.EndsWith(\";\") Then";
if (_content.endsWith(";")) { 
 //BA.debugLineNum = 319;BA.debugLine="Content = Content.SubString2(0, Content.Length -";
_content = _content.substring((int) (0),(int) (_content.length()-1));
 };
 //BA.debugLineNum = 321;BA.debugLine="Return Content";
if (true) return _content;
 //BA.debugLineNum = 322;BA.debugLine="End Sub";
return "";
}
public static void  _confirmexit() throws Exception{
ResumableSub_ConfirmExit rsub = new ResumableSub_ConfirmExit(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_ConfirmExit extends BA.ResumableSub {
public ResumableSub_ConfirmExit(gr.mythologia.main parent) {
this.parent = parent;
}
gr.mythologia.main parent;
int _result = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 661;BA.debugLine="Msgbox2Async(\"Do you want to exit?\", \"Exit\", \"Yes";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Do you want to exit?"),BA.ObjectToCharSequence("Exit"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 662;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 5;
return;
case 5:
//C
this.state = 1;
_result = (Integer) result[0];
;
 //BA.debugLineNum = 663;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
if (true) break;

case 1:
//if
this.state = 4;
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 664;BA.debugLine="Activity.Finish";
parent.mostCurrent._activity.Finish();
 if (true) break;

case 4:
//C
this.state = -1;
;
 //BA.debugLineNum = 666;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _msgbox_result(int _result) throws Exception{
}
public static String  _createdetailpanel() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _pnlh = null;
anywheresoftware.b4a.objects.B4XViewWrapper _bh = null;
anywheresoftware.b4a.objects.PanelWrapper _pnlb = null;
anywheresoftware.b4a.objects.LabelWrapper _lback = null;
anywheresoftware.b4a.objects.B4XViewWrapper _bback = null;
anywheresoftware.b4a.objects.LabelWrapper _lname = null;
anywheresoftware.b4a.objects.ScrollViewWrapper _sv = null;
 //BA.debugLineNum = 214;BA.debugLine="Sub CreateDetailPanel";
 //BA.debugLineNum = 215;BA.debugLine="pnlDetail = xui.CreatePanel(\"pnlDetail\")";
mostCurrent._pnldetail = _xui.CreatePanel(processBA,"pnlDetail");
 //BA.debugLineNum = 216;BA.debugLine="pnlDetail.Color = 0xFF111128";
mostCurrent._pnldetail.setColor(((int)0xff111128));
 //BA.debugLineNum = 217;BA.debugLine="pnlDetail.Visible = False";
mostCurrent._pnldetail.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 218;BA.debugLine="Activity.AddView(pnlDetail, 0, 0, 100%x, 100%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._pnldetail.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 221;BA.debugLine="Dim pnlH As Panel : pnlH.Initialize(\"\")";
_pnlh = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 221;BA.debugLine="Dim pnlH As Panel : pnlH.Initialize(\"\")";
_pnlh.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 222;BA.debugLine="Dim bH As B4XView = pnlH";
_bh = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bh = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_pnlh.getObject()));
 //BA.debugLineNum = 223;BA.debugLine="pnlDetail.AddView(bH, 0, 0, 100%x, 60dip)";
mostCurrent._pnldetail.AddView((android.view.View)(_bh.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
 //BA.debugLineNum = 224;BA.debugLine="bH.Color = 0xFF242445";
_bh.setColor(((int)0xff242445));
 //BA.debugLineNum = 226;BA.debugLine="Dim pnlB As Panel : pnlB.Initialize(\"btnBack\")";
_pnlb = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 226;BA.debugLine="Dim pnlB As Panel : pnlB.Initialize(\"btnBack\")";
_pnlb.Initialize(mostCurrent.activityBA,"btnBack");
 //BA.debugLineNum = 227;BA.debugLine="btnBack = pnlB";
mostCurrent._btnback = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_pnlb.getObject()));
 //BA.debugLineNum = 228;BA.debugLine="bH.AddView(btnBack, 5dip, 5dip, 50dip, 50dip)";
_bh.AddView((android.view.View)(mostCurrent._btnback.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 230;BA.debugLine="Dim lBack As Label : lBack.Initialize(\"btnBack\")";
_lback = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 230;BA.debugLine="Dim lBack As Label : lBack.Initialize(\"btnBack\")";
_lback.Initialize(mostCurrent.activityBA,"btnBack");
 //BA.debugLineNum = 231;BA.debugLine="Dim bBack As B4XView = lBack";
_bback = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bback = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lback.getObject()));
 //BA.debugLineNum = 232;BA.debugLine="bBack.Text = \"<\"";
_bback.setText(BA.ObjectToCharSequence("<"));
 //BA.debugLineNum = 233;BA.debugLine="bBack.TextColor = xui.Color_White";
_bback.setTextColor(_xui.Color_White);
 //BA.debugLineNum = 234;BA.debugLine="bBack.TextSize = 24";
_bback.setTextSize((float) (24));
 //BA.debugLineNum = 235;BA.debugLine="bBack.SetTextAlignment(\"CENTER\", \"CENTER\")";
_bback.SetTextAlignment("CENTER","CENTER");
 //BA.debugLineNum = 236;BA.debugLine="btnBack.AddView(bBack, 0, 0, 50dip, 50dip)";
mostCurrent._btnback.AddView((android.view.View)(_bback.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 238;BA.debugLine="Dim lName As Label : lName.Initialize(\"\")";
_lname = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 238;BA.debugLine="Dim lName As Label : lName.Initialize(\"\")";
_lname.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 239;BA.debugLine="lblDetailName = lName";
mostCurrent._lbldetailname = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lname.getObject()));
 //BA.debugLineNum = 240;BA.debugLine="lblDetailName.TextColor = 0xFFEDD060";
mostCurrent._lbldetailname.setTextColor(((int)0xffedd060));
 //BA.debugLineNum = 241;BA.debugLine="lblDetailName.TextSize = 20";
mostCurrent._lbldetailname.setTextSize((float) (20));
 //BA.debugLineNum = 242;BA.debugLine="lblDetailName.Font = xui.CreateDefaultBoldFont(20";
mostCurrent._lbldetailname.setFont(_xui.CreateDefaultBoldFont((float) (20)));
 //BA.debugLineNum = 243;BA.debugLine="bH.AddView(lblDetailName, 60dip, 10dip, 100%x - 7";
_bh.AddView((android.view.View)(mostCurrent._lbldetailname.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 246;BA.debugLine="Dim sv As ScrollView : sv.Initialize(1000dip)";
_sv = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 246;BA.debugLine="Dim sv As ScrollView : sv.Initialize(1000dip)";
_sv.Initialize(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1000)));
 //BA.debugLineNum = 247;BA.debugLine="svDetail = sv";
mostCurrent._svdetail = _sv;
 //BA.debugLineNum = 248;BA.debugLine="pnlDetail.AddView(svDetail, 0, 60dip, 100%x, 100%";
mostCurrent._pnldetail.AddView((android.view.View)(mostCurrent._svdetail.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60))));
 //BA.debugLineNum = 249;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.B4XViewWrapper  _createitem(anywheresoftware.b4a.objects.collections.Map _entry,int _width,int _height) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.B4XViewWrapper _bp = null;
anywheresoftware.b4a.objects.PanelWrapper _pbg = null;
anywheresoftware.b4a.objects.B4XViewWrapper _bbg = null;
anywheresoftware.b4a.objects.B4XViewWrapper _pnlimg = null;
String _id = "";
anywheresoftware.b4a.objects.collections.List _imgs = null;
String _imgname = "";
anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper _bmp = null;
anywheresoftware.b4a.objects.ImageViewWrapper _iv = null;
anywheresoftware.b4a.objects.B4XViewWrapper _biv = null;
anywheresoftware.b4a.objects.LabelWrapper _lblname = null;
anywheresoftware.b4a.objects.B4XViewWrapper _bname = null;
String _rawname = "";
 //BA.debugLineNum = 415;BA.debugLine="Sub CreateItem(Entry As Map, Width As Int, Height";
 //BA.debugLineNum = 416;BA.debugLine="Dim p As Panel : p.Initialize(\"pnlItem\")";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 416;BA.debugLine="Dim p As Panel : p.Initialize(\"pnlItem\")";
_p.Initialize(mostCurrent.activityBA,"pnlItem");
 //BA.debugLineNum = 417;BA.debugLine="Dim bP As B4XView = p";
_bp = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bp = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_p.getObject()));
 //BA.debugLineNum = 418;BA.debugLine="bP.Tag = Entry";
_bp.setTag((Object)(_entry.getObject()));
 //BA.debugLineNum = 419;BA.debugLine="bP.SetLayoutAnimated(0, 0, 0, Width, Height)";
_bp.SetLayoutAnimated((int) (0),(int) (0),(int) (0),_width,_height);
 //BA.debugLineNum = 422;BA.debugLine="Dim pBg As Panel : pBg.Initialize(\"pnlItem\")";
_pbg = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 422;BA.debugLine="Dim pBg As Panel : pBg.Initialize(\"pnlItem\")";
_pbg.Initialize(mostCurrent.activityBA,"pnlItem");
 //BA.debugLineNum = 423;BA.debugLine="Dim bBg As B4XView = pBg";
_bbg = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bbg = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_pbg.getObject()));
 //BA.debugLineNum = 424;BA.debugLine="bBg.Tag = Entry";
_bbg.setTag((Object)(_entry.getObject()));
 //BA.debugLineNum = 425;BA.debugLine="bP.AddView(bBg, 2dip, 2dip, Width - 4dip, Height";
_bp.AddView((android.view.View)(_bbg.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),(int) (_width-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4))),(int) (_height-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4))));
 //BA.debugLineNum = 426;BA.debugLine="bBg.Color = 0xFF1A1A35";
_bbg.setColor(((int)0xff1a1a35));
 //BA.debugLineNum = 429;BA.debugLine="Dim pnlImg As B4XView = xui.CreatePanel(\"\")";
_pnlimg = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnlimg = _xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 430;BA.debugLine="pBg.AddView(pnlImg, 8dip, 8dip, 54dip, 54dip) ' S";
_pbg.AddView((android.view.View)(_pnlimg.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (54)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (54)));
 //BA.debugLineNum = 431;BA.debugLine="pnlImg.Color = 0xFF242445";
_pnlimg.setColor(((int)0xff242445));
 //BA.debugLineNum = 433;BA.debugLine="Dim id As String = \"\" & Entry.Get(\"id\")";
_id = ""+BA.ObjectToString(_entry.Get((Object)("id")));
 //BA.debugLineNum = 434;BA.debugLine="If id.Contains(\".\") Then id = id.SubString2(0, id";
if (_id.contains(".")) { 
_id = _id.substring((int) (0),_id.indexOf("."));};
 //BA.debugLineNum = 436;BA.debugLine="If ImageMap.ContainsKey(id) Then";
if (_imagemap.ContainsKey((Object)(_id))) { 
 //BA.debugLineNum = 437;BA.debugLine="Dim imgs As List = ImageMap.Get(id)";
_imgs = new anywheresoftware.b4a.objects.collections.List();
_imgs = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_imagemap.Get((Object)(_id))));
 //BA.debugLineNum = 438;BA.debugLine="If imgs.Size > 0 Then";
if (_imgs.getSize()>0) { 
 //BA.debugLineNum = 439;BA.debugLine="Try";
try { //BA.debugLineNum = 440;BA.debugLine="Dim imgName As String = imgs.Get(0)";
_imgname = BA.ObjectToString(_imgs.Get((int) (0)));
 //BA.debugLineNum = 442;BA.debugLine="Dim bmp As B4XBitmap = xui.LoadBitmapResize(Fi";
_bmp = new anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper();
_bmp = _xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"images/images/"+_imgname,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (54)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (54)),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 443;BA.debugLine="Dim iv As ImageView : iv.Initialize(\"\")";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 443;BA.debugLine="Dim iv As ImageView : iv.Initialize(\"\")";
_iv.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 444;BA.debugLine="Dim biv As B4XView = iv";
_biv = new anywheresoftware.b4a.objects.B4XViewWrapper();
_biv = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_iv.getObject()));
 //BA.debugLineNum = 445;BA.debugLine="biv.SetBitmap(bmp)";
_biv.SetBitmap((android.graphics.Bitmap)(_bmp.getObject()));
 //BA.debugLineNum = 446;BA.debugLine="pnlImg.AddView(biv, 0, 0, 54dip, 54dip)";
_pnlimg.AddView((android.view.View)(_biv.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (54)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (54)));
 } 
       catch (Exception e29) {
			processBA.setLastException(e29); //BA.debugLineNum = 448;BA.debugLine="Log(\"Image Load Fail: \" & imgs.Get(0))";
anywheresoftware.b4a.keywords.Common.LogImpl("4786465","Image Load Fail: "+BA.ObjectToString(_imgs.Get((int) (0))),0);
 };
 };
 };
 //BA.debugLineNum = 454;BA.debugLine="Dim lblName As Label : lblName.Initialize(\"\")";
_lblname = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 454;BA.debugLine="Dim lblName As Label : lblName.Initialize(\"\")";
_lblname.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 455;BA.debugLine="Dim bName As B4XView = lblName";
_bname = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bname = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lblname.getObject()));
 //BA.debugLineNum = 456;BA.debugLine="bName.TextColor = 0xFFEDD060";
_bname.setTextColor(((int)0xffedd060));
 //BA.debugLineNum = 457;BA.debugLine="bName.TextSize = 18";
_bname.setTextSize((float) (18));
 //BA.debugLineNum = 458;BA.debugLine="bName.Font = xui.CreateDefaultBoldFont(18)";
_bname.setFont(_xui.CreateDefaultBoldFont((float) (18)));
 //BA.debugLineNum = 459;BA.debugLine="Dim rawName As String = Entry.Get(\"name\")";
_rawname = BA.ObjectToString(_entry.Get((Object)("name")));
 //BA.debugLineNum = 460;BA.debugLine="If CurrentLanguage = \"el\" Then";
if ((_currentlanguage).equals("el")) { 
 //BA.debugLineNum = 461;BA.debugLine="bName.Text = StripAccents(rawName)";
_bname.setText(BA.ObjectToCharSequence(_stripaccents(_rawname)));
 }else {
 //BA.debugLineNum = 463;BA.debugLine="bName.Text = rawName";
_bname.setText(BA.ObjectToCharSequence(_rawname));
 };
 //BA.debugLineNum = 465;BA.debugLine="bName.SetTextAlignment(\"CENTER\", \"LEFT\")";
_bname.SetTextAlignment("CENTER","LEFT");
 //BA.debugLineNum = 466;BA.debugLine="pBg.AddView(bName, 75dip, 0, Width - 85dip, Heigh";
_pbg.AddView((android.view.View)(_bname.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (75)),(int) (0),(int) (_width-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (85))),(int) (_height-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 468;BA.debugLine="Return p";
if (true) return (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_p.getObject()));
 //BA.debugLineNum = 469;BA.debugLine="End Sub";
return null;
}
public static String  _createsplashscreen() throws Exception{
anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper _bmpsplash = null;
anywheresoftware.b4a.objects.ImageViewWrapper _ivsplash = null;
anywheresoftware.b4a.objects.B4XViewWrapper _biv = null;
anywheresoftware.b4a.objects.B4XViewWrapper _pnloverlay = null;
anywheresoftware.b4a.objects.LabelWrapper _lblicon = null;
anywheresoftware.b4a.objects.B4XViewWrapper _bicon = null;
anywheresoftware.b4a.objects.LabelWrapper _lbltitle = null;
anywheresoftware.b4a.objects.B4XViewWrapper _btitle = null;
anywheresoftware.b4a.objects.LabelWrapper _lblload = null;
anywheresoftware.b4a.objects.B4XViewWrapper _bload = null;
 //BA.debugLineNum = 134;BA.debugLine="Sub CreateSplashScreen";
 //BA.debugLineNum = 135;BA.debugLine="pnlSplash = xui.CreatePanel(\"\")";
mostCurrent._pnlsplash = _xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 138;BA.debugLine="Try";
try { //BA.debugLineNum = 139;BA.debugLine="Dim bmpSplash As B4XBitmap = xui.LoadBitmapResiz";
_bmpsplash = new anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper();
_bmpsplash = _xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"images/images/zeus_majestic_entry.png",anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 140;BA.debugLine="Dim ivSplash As ImageView : ivSplash.Initialize(";
_ivsplash = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 140;BA.debugLine="Dim ivSplash As ImageView : ivSplash.Initialize(";
_ivsplash.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 141;BA.debugLine="Dim bIV As B4XView = ivSplash";
_biv = new anywheresoftware.b4a.objects.B4XViewWrapper();
_biv = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_ivsplash.getObject()));
 //BA.debugLineNum = 142;BA.debugLine="bIV.SetBitmap(bmpSplash)";
_biv.SetBitmap((android.graphics.Bitmap)(_bmpsplash.getObject()));
 //BA.debugLineNum = 143;BA.debugLine="pnlSplash.AddView(bIV, 0, 0, 100%x, 100%y)";
mostCurrent._pnlsplash.AddView((android.view.View)(_biv.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 } 
       catch (Exception e10) {
			processBA.setLastException(e10); //BA.debugLineNum = 145;BA.debugLine="Log(\"Splash Image Fail: \" & LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("4196619","Splash Image Fail: "+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 //BA.debugLineNum = 146;BA.debugLine="pnlSplash.Color = 0xFF111128";
mostCurrent._pnlsplash.setColor(((int)0xff111128));
 };
 //BA.debugLineNum = 150;BA.debugLine="Dim pnlOverlay As B4XView = xui.CreatePanel(\"\")";
_pnloverlay = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnloverlay = _xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 151;BA.debugLine="pnlOverlay.Color = xui.Color_ARGB(100, 17, 17, 40";
_pnloverlay.setColor(_xui.Color_ARGB((int) (100),(int) (17),(int) (17),(int) (40)));
 //BA.debugLineNum = 152;BA.debugLine="pnlSplash.AddView(pnlOverlay, 0, 0, 100%x, 100%y)";
mostCurrent._pnlsplash.AddView((android.view.View)(_pnloverlay.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 154;BA.debugLine="Activity.AddView(pnlSplash, 0, 0, 100%x, 100%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._pnlsplash.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 157;BA.debugLine="Dim lblIcon As Label : lblIcon.Initialize(\"\")";
_lblicon = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 157;BA.debugLine="Dim lblIcon As Label : lblIcon.Initialize(\"\")";
_lblicon.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 158;BA.debugLine="Dim bIcon As B4XView = lblIcon";
_bicon = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bicon = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lblicon.getObject()));
 //BA.debugLineNum = 159;BA.debugLine="bIcon.Text = \"🏛️\"";
_bicon.setText(BA.ObjectToCharSequence("🏛️"));
 //BA.debugLineNum = 160;BA.debugLine="bIcon.TextSize = 80";
_bicon.setTextSize((float) (80));
 //BA.debugLineNum = 161;BA.debugLine="bIcon.SetTextAlignment(\"CENTER\", \"CENTER\")";
_bicon.SetTextAlignment("CENTER","CENTER");
 //BA.debugLineNum = 162;BA.debugLine="pnlSplash.AddView(bIcon, 0, 30%y, 100%x, 100dip)";
mostCurrent._pnlsplash.AddView((android.view.View)(_bicon.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (30),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)));
 //BA.debugLineNum = 165;BA.debugLine="Dim lblTitle As Label : lblTitle.Initialize(\"\")";
_lbltitle = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 165;BA.debugLine="Dim lblTitle As Label : lblTitle.Initialize(\"\")";
_lbltitle.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 166;BA.debugLine="Dim bTitle As B4XView = lblTitle";
_btitle = new anywheresoftware.b4a.objects.B4XViewWrapper();
_btitle = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lbltitle.getObject()));
 //BA.debugLineNum = 167;BA.debugLine="bTitle.Text = \"NEW MYTHOLOGY\"";
_btitle.setText(BA.ObjectToCharSequence("NEW MYTHOLOGY"));
 //BA.debugLineNum = 168;BA.debugLine="bTitle.TextColor = 0xFFEDD060";
_btitle.setTextColor(((int)0xffedd060));
 //BA.debugLineNum = 169;BA.debugLine="bTitle.TextSize = 34";
_btitle.setTextSize((float) (34));
 //BA.debugLineNum = 170;BA.debugLine="bTitle.Font = xui.CreateDefaultBoldFont(34)";
_btitle.setFont(_xui.CreateDefaultBoldFont((float) (34)));
 //BA.debugLineNum = 171;BA.debugLine="bTitle.SetTextAlignment(\"CENTER\", \"CENTER\")";
_btitle.SetTextAlignment("CENTER","CENTER");
 //BA.debugLineNum = 172;BA.debugLine="pnlSplash.AddView(bTitle, 0, 45%y, 100%x, 50dip)";
mostCurrent._pnlsplash.AddView((android.view.View)(_btitle.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (45),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 175;BA.debugLine="Dim lblLoad As Label : lblLoad.Initialize(\"\")";
_lblload = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 175;BA.debugLine="Dim lblLoad As Label : lblLoad.Initialize(\"\")";
_lblload.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 176;BA.debugLine="Dim bLoad As B4XView = lblLoad";
_bload = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bload = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lblload.getObject()));
 //BA.debugLineNum = 177;BA.debugLine="bLoad.Text = \"Loading myths...\"";
_bload.setText(BA.ObjectToCharSequence("Loading myths..."));
 //BA.debugLineNum = 178;BA.debugLine="bLoad.TextColor = 0xFFA89878";
_bload.setTextColor(((int)0xffa89878));
 //BA.debugLineNum = 179;BA.debugLine="bLoad.TextSize = 16";
_bload.setTextSize((float) (16));
 //BA.debugLineNum = 180;BA.debugLine="bLoad.SetTextAlignment(\"CENTER\", \"CENTER\")";
_bload.SetTextAlignment("CENTER","CENTER");
 //BA.debugLineNum = 181;BA.debugLine="pnlSplash.AddView(bLoad, 0, 80%y, 100%x, 30dip)";
mostCurrent._pnlsplash.AddView((android.view.View)(_bload.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (80),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 182;BA.debugLine="End Sub";
return "";
}
public static String  _filllist(String _query) throws Exception{
int _cury = 0;
int _itemheight = 0;
anywheresoftware.b4a.objects.collections.List _olympians = null;
String _godid = "";
anywheresoftware.b4a.objects.collections.Map _entry = null;
String _id = "";
String _name = "";
String _desc = "";
boolean _match = false;
anywheresoftware.b4a.objects.B4XViewWrapper _p = null;
boolean _isolympian = false;
 //BA.debugLineNum = 324;BA.debugLine="Sub FillList(Query As String)";
 //BA.debugLineNum = 325;BA.debugLine="If MainUIReady = False Then Return";
if (_mainuiready==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 326;BA.debugLine="svEntries.Panel.RemoveAllViews";
mostCurrent._sventries.getPanel().RemoveAllViews();
 //BA.debugLineNum = 327;BA.debugLine="Dim curY As Int = 0";
_cury = (int) (0);
 //BA.debugLineNum = 328;BA.debugLine="Dim itemHeight As Int = 70dip";
_itemheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70));
 //BA.debugLineNum = 329;BA.debugLine="Query = Query.Trim";
_query = _query.trim();
 //BA.debugLineNum = 330;BA.debugLine="If CurrentLanguage = \"el\" Then";
if ((_currentlanguage).equals("el")) { 
 //BA.debugLineNum = 331;BA.debugLine="Query = StripAccents(Query)";
_query = _stripaccents(_query);
 }else {
 //BA.debugLineNum = 333;BA.debugLine="Query = Query.ToLowerCase";
_query = _query.toLowerCase();
 };
 //BA.debugLineNum = 337;BA.debugLine="Dim Olympians As List";
_olympians = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 338;BA.debugLine="Olympians.Initialize2(Array As String(\"1\", \"203\",";
_olympians.Initialize2(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"1","203","2","62","28","12","36","23","16","102","81","86","66"}));
 //BA.debugLineNum = 341;BA.debugLine="For Each godID As String In Olympians";
{
final anywheresoftware.b4a.BA.IterableList group13 = _olympians;
final int groupLen13 = group13.getSize()
;int index13 = 0;
;
for (; index13 < groupLen13;index13++){
_godid = BA.ObjectToString(group13.Get(index13));
 //BA.debugLineNum = 342;BA.debugLine="For Each entry As Map In MythologyData";
_entry = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group14 = _mythologydata;
final int groupLen14 = group14.getSize()
;int index14 = 0;
;
for (; index14 < groupLen14;index14++){
_entry = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group14.Get(index14)));
 //BA.debugLineNum = 343;BA.debugLine="Dim id As String = \"\" & entry.Get(\"id\")";
_id = ""+BA.ObjectToString(_entry.Get((Object)("id")));
 //BA.debugLineNum = 344;BA.debugLine="If id.Contains(\".\") Then id = id.SubString2(0,";
if (_id.contains(".")) { 
_id = _id.substring((int) (0),_id.indexOf("."));};
 //BA.debugLineNum = 346;BA.debugLine="If id = godID Then";
if ((_id).equals(_godid)) { 
 //BA.debugLineNum = 347;BA.debugLine="Dim name As String = entry.Get(\"name\")";
_name = BA.ObjectToString(_entry.Get((Object)("name")));
 //BA.debugLineNum = 348;BA.debugLine="Dim desc As String = entry.Get(\"description\")";
_desc = BA.ObjectToString(_entry.Get((Object)("description")));
 //BA.debugLineNum = 350;BA.debugLine="Dim match As Boolean = False";
_match = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 351;BA.debugLine="If Query = \"\" Then";
if ((_query).equals("")) { 
 //BA.debugLineNum = 352;BA.debugLine="match = True";
_match = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 354;BA.debugLine="If CurrentLanguage = \"el\" Then";
if ((_currentlanguage).equals("el")) { 
 //BA.debugLineNum = 355;BA.debugLine="If StripAccents(name).Contains(Query) Or Str";
if (_stripaccents(_name).contains(_query) || _stripaccents(_desc).contains(_query)) { 
_match = anywheresoftware.b4a.keywords.Common.True;};
 }else {
 //BA.debugLineNum = 357;BA.debugLine="If name.ToLowerCase.Contains(Query) Or desc.";
if (_name.toLowerCase().contains(_query) || _desc.toLowerCase().contains(_query)) { 
_match = anywheresoftware.b4a.keywords.Common.True;};
 };
 };
 //BA.debugLineNum = 361;BA.debugLine="If match Then";
if (_match) { 
 //BA.debugLineNum = 362;BA.debugLine="Dim p As B4XView = CreateItem(entry, svEntrie";
_p = new anywheresoftware.b4a.objects.B4XViewWrapper();
_p = _createitem(_entry,mostCurrent._sventries.getWidth(),_itemheight);
 //BA.debugLineNum = 363;BA.debugLine="svEntries.Panel.AddView(p, 0, curY, svEntries";
mostCurrent._sventries.getPanel().AddView((android.view.View)(_p.getObject()),(int) (0),_cury,mostCurrent._sventries.getWidth(),_itemheight);
 //BA.debugLineNum = 364;BA.debugLine="curY = curY + itemHeight";
_cury = (int) (_cury+_itemheight);
 };
 //BA.debugLineNum = 366;BA.debugLine="Exit ' Found this god, move to next Olympian";
if (true) break;
 };
 }
};
 }
};
 //BA.debugLineNum = 372;BA.debugLine="For Each entry As Map In MythologyData";
_entry = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group39 = _mythologydata;
final int groupLen39 = group39.getSize()
;int index39 = 0;
;
for (; index39 < groupLen39;index39++){
_entry = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group39.Get(index39)));
 //BA.debugLineNum = 373;BA.debugLine="Dim id As String = \"\" & entry.Get(\"id\")";
_id = ""+BA.ObjectToString(_entry.Get((Object)("id")));
 //BA.debugLineNum = 374;BA.debugLine="If id.Contains(\".\") Then id = id.SubString2(0, i";
if (_id.contains(".")) { 
_id = _id.substring((int) (0),_id.indexOf("."));};
 //BA.debugLineNum = 377;BA.debugLine="Dim isOlympian As Boolean = False";
_isolympian = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 378;BA.debugLine="For Each godID As String In Olympians";
{
final anywheresoftware.b4a.BA.IterableList group43 = _olympians;
final int groupLen43 = group43.getSize()
;int index43 = 0;
;
for (; index43 < groupLen43;index43++){
_godid = BA.ObjectToString(group43.Get(index43));
 //BA.debugLineNum = 379;BA.debugLine="If id = godID Then";
if ((_id).equals(_godid)) { 
 //BA.debugLineNum = 380;BA.debugLine="isOlympian = True";
_isolympian = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 381;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 //BA.debugLineNum = 385;BA.debugLine="If isOlympian = False Then";
if (_isolympian==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 386;BA.debugLine="Dim name As String = entry.Get(\"name\")";
_name = BA.ObjectToString(_entry.Get((Object)("name")));
 //BA.debugLineNum = 387;BA.debugLine="Dim desc As String = entry.Get(\"description\")";
_desc = BA.ObjectToString(_entry.Get((Object)("description")));
 //BA.debugLineNum = 389;BA.debugLine="Dim match As Boolean = False";
_match = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 390;BA.debugLine="If Query = \"\" Then";
if ((_query).equals("")) { 
 //BA.debugLineNum = 391;BA.debugLine="match = True";
_match = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 393;BA.debugLine="If CurrentLanguage = \"el\" Then";
if ((_currentlanguage).equals("el")) { 
 //BA.debugLineNum = 394;BA.debugLine="If StripAccents(name).Contains(Query) Or Stri";
if (_stripaccents(_name).contains(_query) || _stripaccents(_desc).contains(_query)) { 
_match = anywheresoftware.b4a.keywords.Common.True;};
 }else {
 //BA.debugLineNum = 396;BA.debugLine="If name.ToLowerCase.Contains(Query) Or desc.T";
if (_name.toLowerCase().contains(_query) || _desc.toLowerCase().contains(_query)) { 
_match = anywheresoftware.b4a.keywords.Common.True;};
 };
 };
 //BA.debugLineNum = 400;BA.debugLine="If match Then";
if (_match) { 
 //BA.debugLineNum = 401;BA.debugLine="Dim p As B4XView = CreateItem(entry, svEntries";
_p = new anywheresoftware.b4a.objects.B4XViewWrapper();
_p = _createitem(_entry,mostCurrent._sventries.getWidth(),_itemheight);
 //BA.debugLineNum = 402;BA.debugLine="svEntries.Panel.AddView(p, 0, curY, svEntries.";
mostCurrent._sventries.getPanel().AddView((android.view.View)(_p.getObject()),(int) (0),_cury,mostCurrent._sventries.getWidth(),_itemheight);
 //BA.debugLineNum = 403;BA.debugLine="curY = curY + itemHeight";
_cury = (int) (_cury+_itemheight);
 };
 };
 }
};
 //BA.debugLineNum = 407;BA.debugLine="svEntries.Panel.Height = curY";
mostCurrent._sventries.getPanel().setHeight(_cury);
 //BA.debugLineNum = 408;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 29;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 32;BA.debugLine="Private svEntries As ScrollView";
mostCurrent._sventries = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private pnlMain As B4XView";
mostCurrent._pnlmain = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private txtSearch As B4XView";
mostCurrent._txtsearch = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private pnlDetail As B4XView";
mostCurrent._pnldetail = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private lblDetailName As B4XView";
mostCurrent._lbldetailname = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private lblDetailDesc As B4XView ' This will be a";
mostCurrent._lbldetaildesc = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private svDetail As ScrollView";
mostCurrent._svdetail = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private btnBack As B4XView";
mostCurrent._btnback = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private lblLang As B4XView ' Global reference for";
mostCurrent._lbllang = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private pnlSplash As B4XView ' Premium splash scr";
mostCurrent._pnlsplash = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private tmrSearch As Timer ' Debouncer for search";
mostCurrent._tmrsearch = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 45;BA.debugLine="End Sub";
return "";
}
public static String  _lblclear_click() throws Exception{
 //BA.debugLineNum = 208;BA.debugLine="Sub lblClear_Click";
 //BA.debugLineNum = 209;BA.debugLine="txtSearch.Text = \"\"";
mostCurrent._txtsearch.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 210;BA.debugLine="svEntries.ScrollPosition = 0 ' Scroll to top";
mostCurrent._sventries.setScrollPosition((int) (0));
 //BA.debugLineNum = 211;BA.debugLine="FillList(\"\")";
_filllist("");
 //BA.debugLineNum = 212;BA.debugLine="End Sub";
return "";
}
public static String  _lbllang_click() throws Exception{
anywheresoftware.b4a.objects.EditTextWrapper _etl = null;
 //BA.debugLineNum = 184;BA.debugLine="Public Sub lblLang_Click";
 //BA.debugLineNum = 185;BA.debugLine="Log(\"Language Clicked\")";
anywheresoftware.b4a.keywords.Common.LogImpl("4262145","Language Clicked",0);
 //BA.debugLineNum = 186;BA.debugLine="If CurrentLanguage = \"el\" Then";
if ((_currentlanguage).equals("el")) { 
 //BA.debugLineNum = 187;BA.debugLine="CurrentLanguage = \"en\"";
_currentlanguage = "en";
 //BA.debugLineNum = 188;BA.debugLine="lblLang.Text = \"EN\"";
mostCurrent._lbllang.setText(BA.ObjectToCharSequence("EN"));
 //BA.debugLineNum = 189;BA.debugLine="MythologyData = MythologyDataEN";
_mythologydata = _mythologydataen;
 }else {
 //BA.debugLineNum = 191;BA.debugLine="CurrentLanguage = \"el\"";
_currentlanguage = "el";
 //BA.debugLineNum = 192;BA.debugLine="lblLang.Text = \"ΕΛ\"";
mostCurrent._lbllang.setText(BA.ObjectToCharSequence("ΕΛ"));
 //BA.debugLineNum = 193;BA.debugLine="MythologyData = MythologyDataEL";
_mythologydata = _mythologydatael;
 };
 //BA.debugLineNum = 197;BA.debugLine="Dim etL As EditText = txtSearch";
_etl = new anywheresoftware.b4a.objects.EditTextWrapper();
_etl = (anywheresoftware.b4a.objects.EditTextWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.EditTextWrapper(), (android.widget.EditText)(mostCurrent._txtsearch.getObject()));
 //BA.debugLineNum = 198;BA.debugLine="If CurrentLanguage = \"el\" Then";
if ((_currentlanguage).equals("el")) { 
 //BA.debugLineNum = 199;BA.debugLine="etL.InputType = Bit.Or(1, 4096)";
_etl.setInputType(anywheresoftware.b4a.keywords.Common.Bit.Or((int) (1),(int) (4096)));
 //BA.debugLineNum = 200;BA.debugLine="txtSearch.Text = txtSearch.Text.ToUpperCase";
mostCurrent._txtsearch.setText(BA.ObjectToCharSequence(mostCurrent._txtsearch.getText().toUpperCase()));
 }else {
 //BA.debugLineNum = 202;BA.debugLine="etL.InputType = 1 ' Normal text";
_etl.setInputType((int) (1));
 };
 //BA.debugLineNum = 205;BA.debugLine="FillList(txtSearch.Text)";
_filllist(mostCurrent._txtsearch.getText());
 //BA.debugLineNum = 206;BA.debugLine="End Sub";
return "";
}
public static String  _link_click(Object _tag) throws Exception{
 //BA.debugLineNum = 639;BA.debugLine="Sub Link_Click (Tag As Object)";
 //BA.debugLineNum = 640;BA.debugLine="Log(\"Link Clicked: \" & Tag)";
anywheresoftware.b4a.keywords.Common.LogImpl("41114113","Link Clicked: "+BA.ObjectToString(_tag),0);
 //BA.debugLineNum = 641;BA.debugLine="If Tag Is Map Then";
if (_tag instanceof java.util.Map) { 
 //BA.debugLineNum = 642;BA.debugLine="ShowDetail(Tag)";
_showdetail((anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_tag)));
 };
 //BA.debugLineNum = 644;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.CSBuilder  _linkify(String _txt) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cs = null;
anywheresoftware.b4a.objects.collections.List _matches = null;
anywheresoftware.b4a.objects.collections.Map _entry = null;
String _name = "";
int _startsearch = 0;
int _idx = 0;
gr.mythologia.main._linkmatch _m = null;
int _lastidx = 0;
int _start = 0;
int _endidx = 0;
anywheresoftware.b4a.objects.collections.Map _e = null;
 //BA.debugLineNum = 573;BA.debugLine="Sub Linkify(txt As String) As CSBuilder";
 //BA.debugLineNum = 574;BA.debugLine="Dim cs As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 575;BA.debugLine="cs.Initialize";
_cs.Initialize();
 //BA.debugLineNum = 582;BA.debugLine="Dim matches As List : matches.Initialize";
_matches = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 582;BA.debugLine="Dim matches As List : matches.Initialize";
_matches.Initialize();
 //BA.debugLineNum = 584;BA.debugLine="For Each entry As Map In MythologyData";
_entry = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group5 = _mythologydata;
final int groupLen5 = group5.getSize()
;int index5 = 0;
;
for (; index5 < groupLen5;index5++){
_entry = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group5.Get(index5)));
 //BA.debugLineNum = 585;BA.debugLine="Dim name As String = entry.Get(\"name\")";
_name = BA.ObjectToString(_entry.Get((Object)("name")));
 //BA.debugLineNum = 586;BA.debugLine="If name.Length < 3 Then Continue ' Skip tiny nam";
if (_name.length()<3) { 
if (true) continue;};
 //BA.debugLineNum = 589;BA.debugLine="Dim startSearch As Int = 0";
_startsearch = (int) (0);
 //BA.debugLineNum = 590;BA.debugLine="Do While True";
while (anywheresoftware.b4a.keywords.Common.True) {
 //BA.debugLineNum = 591;BA.debugLine="Dim idx As Int = txt.ToLowerCase.IndexOf2(name.";
_idx = _txt.toLowerCase().indexOf(_name.toLowerCase(),_startsearch);
 //BA.debugLineNum = 592;BA.debugLine="If idx = -1 Then Exit";
if (_idx==-1) { 
if (true) break;};
 //BA.debugLineNum = 597;BA.debugLine="Dim m As LinkMatch";
_m = new gr.mythologia.main._linkmatch();
 //BA.debugLineNum = 598;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 599;BA.debugLine="m.start = idx";
_m.start /*int*/  = _idx;
 //BA.debugLineNum = 600;BA.debugLine="m.endIdx = idx + name.Length";
_m.endIdx /*int*/  = (int) (_idx+_name.length());
 //BA.debugLineNum = 601;BA.debugLine="m.entry = entry";
_m.entry /*anywheresoftware.b4a.objects.collections.Map*/  = _entry;
 //BA.debugLineNum = 602;BA.debugLine="matches.Add(m)";
_matches.Add((Object)(_m));
 //BA.debugLineNum = 604;BA.debugLine="startSearch = idx + name.Length";
_startsearch = (int) (_idx+_name.length());
 }
;
 }
};
 //BA.debugLineNum = 609;BA.debugLine="matches.SortType(\"start\", True)";
_matches.SortType("start",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 612;BA.debugLine="Dim lastIdx As Int = 0";
_lastidx = (int) (0);
 //BA.debugLineNum = 613;BA.debugLine="For Each m As LinkMatch In matches";
{
final anywheresoftware.b4a.BA.IterableList group23 = _matches;
final int groupLen23 = group23.getSize()
;int index23 = 0;
;
for (; index23 < groupLen23;index23++){
_m = (gr.mythologia.main._linkmatch)(group23.Get(index23));
 //BA.debugLineNum = 614;BA.debugLine="Dim start As Int = m.start";
_start = _m.start /*int*/ ;
 //BA.debugLineNum = 615;BA.debugLine="Dim endIdx As Int = m.endIdx";
_endidx = _m.endIdx /*int*/ ;
 //BA.debugLineNum = 618;BA.debugLine="If start < lastIdx Then Continue";
if (_start<_lastidx) { 
if (true) continue;};
 //BA.debugLineNum = 621;BA.debugLine="cs.Append(txt.SubString2(lastIdx, start))";
_cs.Append(BA.ObjectToCharSequence(_txt.substring(_lastidx,_start)));
 //BA.debugLineNum = 624;BA.debugLine="Dim e As Map = m.entry";
_e = new anywheresoftware.b4a.objects.collections.Map();
_e = _m.entry /*anywheresoftware.b4a.objects.collections.Map*/ ;
 //BA.debugLineNum = 625;BA.debugLine="cs.Color(0xFFEDD060).Underline.Clickable(\"Link\",";
_cs.Color(((int)0xffedd060)).Underline().Clickable(processBA,"Link",(Object)(_e.getObject())).Append(BA.ObjectToCharSequence(_txt.substring(_start,_endidx))).PopAll();
 //BA.debugLineNum = 627;BA.debugLine="lastIdx = endIdx";
_lastidx = _endidx;
 }
};
 //BA.debugLineNum = 631;BA.debugLine="If lastIdx < txt.Length Then";
if (_lastidx<_txt.length()) { 
 //BA.debugLineNum = 632;BA.debugLine="cs.Append(txt.SubString(lastIdx))";
_cs.Append(BA.ObjectToCharSequence(_txt.substring(_lastidx)));
 };
 //BA.debugLineNum = 635;BA.debugLine="Return cs";
if (true) return _cs;
 //BA.debugLineNum = 636;BA.debugLine="End Sub";
return null;
}
public static String  _loaddata() throws Exception{
String _smap = "";
anywheresoftware.b4a.objects.collections.JSONParser _jp = null;
String _sdata = "";
String _sdataen = "";
 //BA.debugLineNum = 255;BA.debugLine="Sub LoadData";
 //BA.debugLineNum = 256;BA.debugLine="Try";
try { //BA.debugLineNum = 258;BA.debugLine="Dim sMap As String = File.ReadString(File.DirAss";
_smap = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"image_map.js");
 //BA.debugLineNum = 259;BA.debugLine="sMap = CleanJS(sMap, \"ENTRY_IMAGES\")";
_smap = _cleanjs(_smap,"ENTRY_IMAGES");
 //BA.debugLineNum = 260;BA.debugLine="Dim jp As JSONParser";
_jp = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 261;BA.debugLine="jp.Initialize(sMap)";
_jp.Initialize(_smap);
 //BA.debugLineNum = 262;BA.debugLine="ImageMap = jp.NextObject";
_imagemap = _jp.NextObject();
 //BA.debugLineNum = 263;BA.debugLine="Log(\"Loaded \" & ImageMap.Size & \" image mappings";
anywheresoftware.b4a.keywords.Common.LogImpl("4524296","Loaded "+BA.NumberToString(_imagemap.getSize())+" image mappings.",0);
 //BA.debugLineNum = 266;BA.debugLine="Dim sData As String = File.ReadString(File.DirAs";
_sdata = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"mythology_data.js");
 //BA.debugLineNum = 267;BA.debugLine="sData = CleanJS(sData, \"MYTHOLOGY_DATA\")";
_sdata = _cleanjs(_sdata,"MYTHOLOGY_DATA");
 //BA.debugLineNum = 268;BA.debugLine="jp.Initialize(sData)";
_jp.Initialize(_sdata);
 //BA.debugLineNum = 269;BA.debugLine="MythologyDataEL = jp.NextArray";
_mythologydatael = _jp.NextArray();
 //BA.debugLineNum = 270;BA.debugLine="Log(\"Loaded \" & MythologyDataEL.Size & \" Greek e";
anywheresoftware.b4a.keywords.Common.LogImpl("4524303","Loaded "+BA.NumberToString(_mythologydatael.getSize())+" Greek entries.",0);
 //BA.debugLineNum = 273;BA.debugLine="Try";
try { //BA.debugLineNum = 274;BA.debugLine="Dim sDataEN As String = File.ReadString(File.Di";
_sdataen = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"en/mythology_data_en.js");
 //BA.debugLineNum = 275;BA.debugLine="sDataEN = CleanJS(sDataEN, \"MYTHOLOGY_DATA\")";
_sdataen = _cleanjs(_sdataen,"MYTHOLOGY_DATA");
 //BA.debugLineNum = 276;BA.debugLine="jp.Initialize(sDataEN)";
_jp.Initialize(_sdataen);
 //BA.debugLineNum = 277;BA.debugLine="MythologyDataEN = jp.NextArray";
_mythologydataen = _jp.NextArray();
 //BA.debugLineNum = 278;BA.debugLine="Log(\"Loaded \" & MythologyDataEN.Size & \" Englis";
anywheresoftware.b4a.keywords.Common.LogImpl("4524311","Loaded "+BA.NumberToString(_mythologydataen.getSize())+" English entries.",0);
 } 
       catch (Exception e20) {
			processBA.setLastException(e20); //BA.debugLineNum = 280;BA.debugLine="Log(\"English data load failed: \" & LastExceptio";
anywheresoftware.b4a.keywords.Common.LogImpl("4524313","English data load failed: "+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),0);
 //BA.debugLineNum = 281;BA.debugLine="MythologyDataEN.Initialize";
_mythologydataen.Initialize();
 };
 //BA.debugLineNum = 285;BA.debugLine="If CurrentLanguage = \"en\" Then";
if ((_currentlanguage).equals("en")) { 
 //BA.debugLineNum = 286;BA.debugLine="MythologyData = MythologyDataEN";
_mythologydata = _mythologydataen;
 }else {
 //BA.debugLineNum = 288;BA.debugLine="MythologyData = MythologyDataEL";
_mythologydata = _mythologydatael;
 };
 } 
       catch (Exception e29) {
			processBA.setLastException(e29); //BA.debugLineNum = 292;BA.debugLine="Log(\"LoadData Error: \" & LastException.Message)";
anywheresoftware.b4a.keywords.Common.LogImpl("4524325","LoadData Error: "+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),0);
 //BA.debugLineNum = 293;BA.debugLine="MsgboxAsync(\"Error loading data: \" & LastExcepti";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Error loading data: "+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),BA.ObjectToCharSequence("Error"),processBA);
 };
 //BA.debugLineNum = 295;BA.debugLine="End Sub";
return "";
}
public static String  _pnlitem_click() throws Exception{
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;
 //BA.debugLineNum = 410;BA.debugLine="Sub pnlItem_Click";
 //BA.debugLineNum = 411;BA.debugLine="Dim pnl As B4XView = Sender";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 412;BA.debugLine="ShowDetail(pnl.Tag)";
_showdetail((anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_pnl.getTag())));
 //BA.debugLineNum = 413;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        b4a.example.dateutils._process_globals();
main._process_globals();
starter._process_globals();
xuiviewsutils._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 19;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 20;BA.debugLine="Public MythologyData As List ' Current active lis";
_mythologydata = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 21;BA.debugLine="Public MythologyDataEL As List";
_mythologydatael = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 22;BA.debugLine="Public MythologyDataEN As List";
_mythologydataen = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 23;BA.debugLine="Public ImageMap As Map";
_imagemap = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 24;BA.debugLine="Public CurrentLanguage As String = \"el\"";
_currentlanguage = "el";
 //BA.debugLineNum = 25;BA.debugLine="Private MainUIReady As Boolean = False ' Flag to";
_mainuiready = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 26;BA.debugLine="Type LinkMatch (start As Int, endIdx As Int, entr";
;
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
public static String  _showdetail(anywheresoftware.b4a.objects.collections.Map _entry) throws Exception{
String _rawname = "";
int _cury = 0;
String _id = "";
anywheresoftware.b4a.objects.collections.List _imgs = null;
anywheresoftware.b4a.objects.HorizontalScrollViewWrapper _hsv = null;
int _curx = 0;
String _imgname = "";
anywheresoftware.b4a.objects.B4XViewWrapper _pnlimg = null;
anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper _bmp = null;
anywheresoftware.b4a.objects.ImageViewWrapper _iv = null;
anywheresoftware.b4a.objects.B4XViewWrapper _biv = null;
anywheresoftware.b4a.objects.LabelWrapper _lblheader = null;
anywheresoftware.b4a.objects.B4XViewWrapper _bheader = null;
anywheresoftware.b4a.objects.LabelWrapper _lbld = null;
anywheresoftware.b4a.objects.B4XViewWrapper _bd = null;
String _fulltext = "";
anywheresoftware.b4a.objects.CSBuilder _cs = null;
anywheresoftware.b4j.object.JavaObject _jo = null;
anywheresoftware.b4j.object.JavaObject _linkmethod = null;
int _estimatedlines = 0;
int _textheight = 0;
 //BA.debugLineNum = 488;BA.debugLine="Sub ShowDetail(Entry As Map)";
 //BA.debugLineNum = 489;BA.debugLine="pnlDetail.Visible = True";
mostCurrent._pnldetail.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 490;BA.debugLine="pnlDetail.BringToFront";
mostCurrent._pnldetail.BringToFront();
 //BA.debugLineNum = 491;BA.debugLine="pnlDetail.SetLayoutAnimated(300, 0, 0, 100%x, 100";
mostCurrent._pnldetail.SetLayoutAnimated((int) (300),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 493;BA.debugLine="lblDetailName.Tag = Entry ' Keep original map in";
mostCurrent._lbldetailname.setTag((Object)(_entry.getObject()));
 //BA.debugLineNum = 494;BA.debugLine="Dim rawName As String = Entry.Get(\"name\")";
_rawname = BA.ObjectToString(_entry.Get((Object)("name")));
 //BA.debugLineNum = 495;BA.debugLine="If CurrentLanguage = \"el\" Then";
if ((_currentlanguage).equals("el")) { 
 //BA.debugLineNum = 496;BA.debugLine="lblDetailName.Text = StripAccents(rawName)";
mostCurrent._lbldetailname.setText(BA.ObjectToCharSequence(_stripaccents(_rawname)));
 }else {
 //BA.debugLineNum = 498;BA.debugLine="lblDetailName.Text = rawName";
mostCurrent._lbldetailname.setText(BA.ObjectToCharSequence(_rawname));
 };
 //BA.debugLineNum = 502;BA.debugLine="svDetail.Panel.RemoveAllViews";
mostCurrent._svdetail.getPanel().RemoveAllViews();
 //BA.debugLineNum = 504;BA.debugLine="Dim curY As Int = 15dip";
_cury = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15));
 //BA.debugLineNum = 507;BA.debugLine="Dim id As String = Entry.Get(\"id\")";
_id = BA.ObjectToString(_entry.Get((Object)("id")));
 //BA.debugLineNum = 508;BA.debugLine="If ImageMap.ContainsKey(id) Then";
if (_imagemap.ContainsKey((Object)(_id))) { 
 //BA.debugLineNum = 509;BA.debugLine="Dim imgs As List = ImageMap.Get(id)";
_imgs = new anywheresoftware.b4a.objects.collections.List();
_imgs = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_imagemap.Get((Object)(_id))));
 //BA.debugLineNum = 510;BA.debugLine="If imgs.Size > 0 Then";
if (_imgs.getSize()>0) { 
 //BA.debugLineNum = 512;BA.debugLine="Dim hsv As HorizontalScrollView : hsv.Initializ";
_hsv = new anywheresoftware.b4a.objects.HorizontalScrollViewWrapper();
 //BA.debugLineNum = 512;BA.debugLine="Dim hsv As HorizontalScrollView : hsv.Initializ";
_hsv.Initialize(mostCurrent.activityBA,(int) (0),"");
 //BA.debugLineNum = 513;BA.debugLine="svDetail.Panel.AddView(hsv, 0, curY, 100%x, 240";
mostCurrent._svdetail.getPanel().AddView((android.view.View)(_hsv.getObject()),(int) (0),_cury,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)));
 //BA.debugLineNum = 515;BA.debugLine="Dim curX As Int = 15dip";
_curx = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15));
 //BA.debugLineNum = 516;BA.debugLine="for each imgName as String in imgs";
{
final anywheresoftware.b4a.BA.IterableList group21 = _imgs;
final int groupLen21 = group21.getSize()
;int index21 = 0;
;
for (; index21 < groupLen21;index21++){
_imgname = BA.ObjectToString(group21.Get(index21));
 //BA.debugLineNum = 517;BA.debugLine="Dim pnlImg As B4XView = xui.CreatePanel(\"\")";
_pnlimg = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnlimg = _xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 518;BA.debugLine="hsv.Panel.AddView(pnlImg, curX, 10dip, 300dip,";
_hsv.getPanel().AddView((android.view.View)(_pnlimg.getObject()),_curx,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200)));
 //BA.debugLineNum = 519;BA.debugLine="pnlImg.Color = 0xFF242445";
_pnlimg.setColor(((int)0xff242445));
 //BA.debugLineNum = 521;BA.debugLine="Try";
try { //BA.debugLineNum = 522;BA.debugLine="Dim bmp As B4XBitmap = xui.LoadBitmapResize(F";
_bmp = new anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper();
_bmp = _xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"images/images/"+_imgname,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200)),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 523;BA.debugLine="Dim iv As ImageView : iv.Initialize(\"\")";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 523;BA.debugLine="Dim iv As ImageView : iv.Initialize(\"\")";
_iv.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 524;BA.debugLine="Dim biv As B4XView = iv";
_biv = new anywheresoftware.b4a.objects.B4XViewWrapper();
_biv = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_iv.getObject()));
 //BA.debugLineNum = 525;BA.debugLine="biv.SetBitmap(bmp)";
_biv.SetBitmap((android.graphics.Bitmap)(_bmp.getObject()));
 //BA.debugLineNum = 526;BA.debugLine="pnlImg.AddView(biv, 0, 0, 300dip, 200dip)";
_pnlimg.AddView((android.view.View)(_biv.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200)));
 //BA.debugLineNum = 527;BA.debugLine="curX = curX + 315dip";
_curx = (int) (_curx+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (315)));
 } 
       catch (Exception e34) {
			processBA.setLastException(e34); //BA.debugLineNum = 529;BA.debugLine="Log(\"Detail Image Load Fail: \" & imgName)";
anywheresoftware.b4a.keywords.Common.LogImpl("4983081","Detail Image Load Fail: "+_imgname,0);
 };
 }
};
 //BA.debugLineNum = 532;BA.debugLine="hsv.Panel.Width = curX + 15dip";
_hsv.getPanel().setWidth((int) (_curx+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15))));
 //BA.debugLineNum = 533;BA.debugLine="curY = curY + 240dip";
_cury = (int) (_cury+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)));
 };
 };
 //BA.debugLineNum = 538;BA.debugLine="Dim lblHeader As Label : lblHeader.Initialize(\"\")";
_lblheader = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 538;BA.debugLine="Dim lblHeader As Label : lblHeader.Initialize(\"\")";
_lblheader.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 539;BA.debugLine="Dim bHeader As B4XView = lblHeader";
_bheader = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bheader = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lblheader.getObject()));
 //BA.debugLineNum = 540;BA.debugLine="bHeader.Text = \"ΠΕΡΙΓΡΑΦΗ\" ' Description in Greek";
_bheader.setText(BA.ObjectToCharSequence("ΠΕΡΙΓΡΑΦΗ"));
 //BA.debugLineNum = 541;BA.debugLine="bHeader.TextColor = 0xFFA89878";
_bheader.setTextColor(((int)0xffa89878));
 //BA.debugLineNum = 542;BA.debugLine="bHeader.TextSize = 14";
_bheader.setTextSize((float) (14));
 //BA.debugLineNum = 543;BA.debugLine="bHeader.Font = xui.CreateDefaultBoldFont(14)";
_bheader.setFont(_xui.CreateDefaultBoldFont((float) (14)));
 //BA.debugLineNum = 544;BA.debugLine="svDetail.Panel.AddView(bHeader, 15dip, curY, 100%";
mostCurrent._svdetail.getPanel().AddView((android.view.View)(_bheader.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)),_cury,(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 545;BA.debugLine="curY = curY + 35dip";
_cury = (int) (_cury+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (35)));
 //BA.debugLineNum = 548;BA.debugLine="Dim lblD As Label : lblD.Initialize(\"lblStory\")";
_lbld = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 548;BA.debugLine="Dim lblD As Label : lblD.Initialize(\"lblStory\")";
_lbld.Initialize(mostCurrent.activityBA,"lblStory");
 //BA.debugLineNum = 549;BA.debugLine="Dim bD As B4XView = lblD";
_bd = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bd = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lbld.getObject()));
 //BA.debugLineNum = 550;BA.debugLine="bD.TextColor = xui.Color_LightGray";
_bd.setTextColor(_xui.Color_LightGray);
 //BA.debugLineNum = 551;BA.debugLine="bD.TextSize = 17";
_bd.setTextSize((float) (17));
 //BA.debugLineNum = 552;BA.debugLine="Dim fullText As String = Entry.Get(\"description\")";
_fulltext = BA.ObjectToString(_entry.Get((Object)("description")));
 //BA.debugLineNum = 555;BA.debugLine="Dim cs As CSBuilder = Linkify(fullText)";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
_cs = _linkify(_fulltext);
 //BA.debugLineNum = 556;BA.debugLine="bD.Text = cs";
_bd.setText(BA.ObjectToCharSequence(_cs.getObject()));
 //BA.debugLineNum = 559;BA.debugLine="Dim JO As JavaObject = bD";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_bd.getObject()));
 //BA.debugLineNum = 560;BA.debugLine="Dim LinkMethod As JavaObject";
_linkmethod = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 561;BA.debugLine="LinkMethod.InitializeStatic(\"android.text.method.";
_linkmethod.InitializeStatic("android.text.method.LinkMovementMethod");
 //BA.debugLineNum = 562;BA.debugLine="JO.RunMethod(\"setMovementMethod\", Array(LinkMetho";
_jo.RunMethod("setMovementMethod",new Object[]{_linkmethod.RunMethod("getInstance",(Object[])(anywheresoftware.b4a.keywords.Common.Null))});
 //BA.debugLineNum = 565;BA.debugLine="Dim estimatedLines As Int = (fullText.Length / 25";
_estimatedlines = (int) ((_fulltext.length()/(double)25)+10);
 //BA.debugLineNum = 566;BA.debugLine="Dim textHeight As Int = estimatedLines * 25dip";
_textheight = (int) (_estimatedlines*anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (25)));
 //BA.debugLineNum = 568;BA.debugLine="svDetail.Panel.AddView(bD, 15dip, curY, 100%x - 3";
mostCurrent._svdetail.getPanel().AddView((android.view.View)(_bd.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)),_cury,(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30))),_textheight);
 //BA.debugLineNum = 569;BA.debugLine="svDetail.Panel.Height = curY + textHeight + 50dip";
mostCurrent._svdetail.getPanel().setHeight((int) (_cury+_textheight+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50))));
 //BA.debugLineNum = 570;BA.debugLine="End Sub";
return "";
}
public static String  _stripaccents(String _s) throws Exception{
String _out = "";
 //BA.debugLineNum = 668;BA.debugLine="Sub StripAccents(s As String) As String";
 //BA.debugLineNum = 669;BA.debugLine="If s = \"\" Then Return \"\"";
if ((_s).equals("")) { 
if (true) return "";};
 //BA.debugLineNum = 670;BA.debugLine="Dim out As String = s.ToUpperCase";
_out = _s.toUpperCase();
 //BA.debugLineNum = 671;BA.debugLine="out = out.Replace(\"Ά\", \"Α\").Replace(\"Έ\", \"Ε\").Rep";
_out = _out.replace("Ά","Α").replace("Έ","Ε").replace("Ή","Η").replace("Ί","Ι").replace("Ό","Ο").replace("Ύ","Υ").replace("Ώ","Ω");
 //BA.debugLineNum = 672;BA.debugLine="out = out.Replace(\"Ϊ\", \"Ι\").Replace(\"Ϋ\", \"Υ\")";
_out = _out.replace("Ϊ","Ι").replace("Ϋ","Υ");
 //BA.debugLineNum = 674;BA.debugLine="out = out.Replace(\"ά\", \"Α\").Replace(\"έ\", \"Ε\").Rep";
_out = _out.replace("ά","Α").replace("έ","Ε").replace("ή","Η").replace("ί","Ι").replace("ό","Ο").replace("ύ","Υ").replace("ώ","Ω");
 //BA.debugLineNum = 675;BA.debugLine="out = out.Replace(\"ϊ\", \"Ι\").Replace(\"ϋ\", \"Υ\").Rep";
_out = _out.replace("ϊ","Ι").replace("ϋ","Υ").replace("ΐ","Ι").replace("ΰ","Υ");
 //BA.debugLineNum = 676;BA.debugLine="Return out";
if (true) return _out;
 //BA.debugLineNum = 677;BA.debugLine="End Sub";
return "";
}
public static String  _tmrsearch_tick() throws Exception{
 //BA.debugLineNum = 483;BA.debugLine="Sub tmrSearch_Tick";
 //BA.debugLineNum = 484;BA.debugLine="tmrSearch.Enabled = False";
mostCurrent._tmrsearch.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 485;BA.debugLine="FillList(txtSearch.Text)";
_filllist(mostCurrent._txtsearch.getText());
 //BA.debugLineNum = 486;BA.debugLine="End Sub";
return "";
}
public static String  _txtsearch_textchanged(String _old,String _new) throws Exception{
anywheresoftware.b4a.objects.EditTextWrapper _et = null;
 //BA.debugLineNum = 471;BA.debugLine="Sub txtSearch_TextChanged (Old As String, New As S";
 //BA.debugLineNum = 472;BA.debugLine="If CurrentLanguage = \"el\" And New <> New.ToUpperC";
if ((_currentlanguage).equals("el") && (_new).equals(_new.toUpperCase()) == false) { 
 //BA.debugLineNum = 473;BA.debugLine="txtSearch.Text = New.ToUpperCase";
mostCurrent._txtsearch.setText(BA.ObjectToCharSequence(_new.toUpperCase()));
 //BA.debugLineNum = 475;BA.debugLine="Dim et As EditText = txtSearch";
_et = new anywheresoftware.b4a.objects.EditTextWrapper();
_et = (anywheresoftware.b4a.objects.EditTextWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.EditTextWrapper(), (android.widget.EditText)(mostCurrent._txtsearch.getObject()));
 //BA.debugLineNum = 476;BA.debugLine="et.SelectionStart = et.Text.Length";
_et.setSelectionStart(_et.getText().length());
 //BA.debugLineNum = 477;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 479;BA.debugLine="tmrSearch.Enabled = False";
mostCurrent._tmrsearch.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 480;BA.debugLine="tmrSearch.Enabled = True ' Reset timer";
mostCurrent._tmrsearch.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 481;BA.debugLine="End Sub";
return "";
}


public static void configureWebView(WebView wv) {
    try {
        WebSettings s = wv.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setDatabaseEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(true);
        // Critical: allow file:// pages to load other file:// resources cross-directory
        s.setAllowFileAccessFromFileURLs(true);
        s.setAllowUniversalAccessFromFileURLs(true);
        WebView.setWebContentsDebuggingEnabled(true);
    } catch (Exception e) {
        android.util.Log.e("B4A", "configureWebView error: " + e.getMessage());
    }
}
}
