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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "gr.mythologia", "gr.mythologia.main");
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



public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        b4a.example.dateutils._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}

private static BA killProgramHelper(BA ba) {
    if (ba == null)
        return null;
    anywheresoftware.b4a.BA.SharedProcessBA sharedProcessBA = ba.sharedProcessBA;
    if (sharedProcessBA == null || sharedProcessBA.activityBA == null)
        return null;
    return sharedProcessBA.activityBA.get();
}
public static void killProgram() {
     {
            Activity __a = null;
            if (main.previousOne != null) {
				__a = main.previousOne.get();
			}
            else {
                BA ba = killProgramHelper(main.mostCurrent == null ? null : main.mostCurrent.processBA);
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

BA.applicationContext.stopService(new android.content.Intent(BA.applicationContext, starter.class));
}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public static anywheresoftware.b4a.objects.collections.List _mythologydata = null;
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
public b4a.example.dateutils _dateutils = null;
public gr.mythologia.starter _starter = null;
public gr.mythologia.xuiviewsutils _xuiviewsutils = null;
public static void  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}); return;}
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
anywheresoftware.b4a.objects.LabelWrapper _llang = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="main";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=131073;
 //BA.debugLineNum = 131073;BA.debugLine="Log(\"Activity_Create Start\")";
anywheresoftware.b4a.keywords.Common.LogImpl("2131073","Activity_Create Start",0);
RDebugUtils.currentLine=131074;
 //BA.debugLineNum = 131074;BA.debugLine="MainUIReady = False";
parent._mainuiready = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=131077;
 //BA.debugLineNum = 131077;BA.debugLine="Activity.RemoveAllViews";
parent.mostCurrent._activity.RemoveAllViews();
RDebugUtils.currentLine=131078;
 //BA.debugLineNum = 131078;BA.debugLine="Activity.Color = 0xFF111128";
parent.mostCurrent._activity.setColor(((int)0xff111128));
RDebugUtils.currentLine=131081;
 //BA.debugLineNum = 131081;BA.debugLine="CreateSplashScreen";
_createsplashscreen();
RDebugUtils.currentLine=131084;
 //BA.debugLineNum = 131084;BA.debugLine="If FirstTime Then";
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
RDebugUtils.currentLine=131085;
 //BA.debugLineNum = 131085;BA.debugLine="LoadData";
_loaddata();
 if (true) break;

case 4:
//C
this.state = -1;
;
RDebugUtils.currentLine=131090;
 //BA.debugLineNum = 131090;BA.debugLine="Dim sv As ScrollView : sv.Initialize(0)";
_sv = new anywheresoftware.b4a.objects.ScrollViewWrapper();
RDebugUtils.currentLine=131090;
 //BA.debugLineNum = 131090;BA.debugLine="Dim sv As ScrollView : sv.Initialize(0)";
_sv.Initialize(mostCurrent.activityBA,(int) (0));
RDebugUtils.currentLine=131091;
 //BA.debugLineNum = 131091;BA.debugLine="svEntries = sv";
parent.mostCurrent._sventries = _sv;
RDebugUtils.currentLine=131092;
 //BA.debugLineNum = 131092;BA.debugLine="Activity.AddView(svEntries, 0, 60dip, 100%x, 100%";
parent.mostCurrent._activity.AddView((android.view.View)(parent.mostCurrent._sventries.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60))));
RDebugUtils.currentLine=131093;
 //BA.debugLineNum = 131093;BA.debugLine="svEntries.Visible = False ' Hide until splash end";
parent.mostCurrent._sventries.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=131096;
 //BA.debugLineNum = 131096;BA.debugLine="Dim pHeader As Panel : pHeader.Initialize(\"\")";
_pheader = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=131096;
 //BA.debugLineNum = 131096;BA.debugLine="Dim pHeader As Panel : pHeader.Initialize(\"\")";
_pheader.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=131097;
 //BA.debugLineNum = 131097;BA.debugLine="Dim bHeader As B4XView = pHeader";
_bheader = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bheader = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_pheader.getObject()));
RDebugUtils.currentLine=131098;
 //BA.debugLineNum = 131098;BA.debugLine="Activity.AddView(bHeader, 0, 0, 100%x, 60dip)";
parent.mostCurrent._activity.AddView((android.view.View)(_bheader.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
RDebugUtils.currentLine=131099;
 //BA.debugLineNum = 131099;BA.debugLine="bHeader.Color = 0xFF242445";
_bheader.setColor(((int)0xff242445));
RDebugUtils.currentLine=131100;
 //BA.debugLineNum = 131100;BA.debugLine="bHeader.Visible = False";
_bheader.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=131103;
 //BA.debugLineNum = 131103;BA.debugLine="Dim et As EditText : et.Initialize(\"txtSearch\")";
_et = new anywheresoftware.b4a.objects.EditTextWrapper();
RDebugUtils.currentLine=131103;
 //BA.debugLineNum = 131103;BA.debugLine="Dim et As EditText : et.Initialize(\"txtSearch\")";
_et.Initialize(mostCurrent.activityBA,"txtSearch");
RDebugUtils.currentLine=131104;
 //BA.debugLineNum = 131104;BA.debugLine="et.Hint = \"Search myths...\"";
_et.setHint("Search myths...");
RDebugUtils.currentLine=131105;
 //BA.debugLineNum = 131105;BA.debugLine="et.HintColor = 0xFFA89878";
_et.setHintColor(((int)0xffa89878));
RDebugUtils.currentLine=131106;
 //BA.debugLineNum = 131106;BA.debugLine="et.TextColor = xui.Color_White";
_et.setTextColor(parent._xui.Color_White);
RDebugUtils.currentLine=131107;
 //BA.debugLineNum = 131107;BA.debugLine="et.TextSize = 16";
_et.setTextSize((float) (16));
RDebugUtils.currentLine=131108;
 //BA.debugLineNum = 131108;BA.debugLine="et.Color = 0xFF1A1A35";
_et.setColor(((int)0xff1a1a35));
RDebugUtils.currentLine=131109;
 //BA.debugLineNum = 131109;BA.debugLine="txtSearch = et";
parent.mostCurrent._txtsearch = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_et.getObject()));
RDebugUtils.currentLine=131110;
 //BA.debugLineNum = 131110;BA.debugLine="Dim bET As B4XView = et";
_bet = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bet = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_et.getObject()));
RDebugUtils.currentLine=131111;
 //BA.debugLineNum = 131111;BA.debugLine="bET.SetTextAlignment(\"CENTER\", \"LEFT\")";
_bet.SetTextAlignment("CENTER","LEFT");
RDebugUtils.currentLine=131112;
 //BA.debugLineNum = 131112;BA.debugLine="bHeader.AddView(txtSearch, 10dip, 5dip, 100%x - 8";
_bheader.AddView((android.view.View)(parent.mostCurrent._txtsearch.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=131115;
 //BA.debugLineNum = 131115;BA.debugLine="Dim lLang As Label : lLang.Initialize(\"lblLang\")";
_llang = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=131115;
 //BA.debugLineNum = 131115;BA.debugLine="Dim lLang As Label : lLang.Initialize(\"lblLang\")";
_llang.Initialize(mostCurrent.activityBA,"lblLang");
RDebugUtils.currentLine=131116;
 //BA.debugLineNum = 131116;BA.debugLine="lblLang = lLang";
parent.mostCurrent._lbllang = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_llang.getObject()));
RDebugUtils.currentLine=131117;
 //BA.debugLineNum = 131117;BA.debugLine="lblLang.Text = \"EL\"";
parent.mostCurrent._lbllang.setText(BA.ObjectToCharSequence("EL"));
RDebugUtils.currentLine=131118;
 //BA.debugLineNum = 131118;BA.debugLine="lblLang.TextColor = 0xFFEDD060";
parent.mostCurrent._lbllang.setTextColor(((int)0xffedd060));
RDebugUtils.currentLine=131119;
 //BA.debugLineNum = 131119;BA.debugLine="lblLang.TextSize = 18";
parent.mostCurrent._lbllang.setTextSize((float) (18));
RDebugUtils.currentLine=131120;
 //BA.debugLineNum = 131120;BA.debugLine="lblLang.Font = xui.CreateDefaultBoldFont(18)";
parent.mostCurrent._lbllang.setFont(parent._xui.CreateDefaultBoldFont((float) (18)));
RDebugUtils.currentLine=131121;
 //BA.debugLineNum = 131121;BA.debugLine="lblLang.SetTextAlignment(\"CENTER\", \"CENTER\")";
parent.mostCurrent._lbllang.SetTextAlignment("CENTER","CENTER");
RDebugUtils.currentLine=131122;
 //BA.debugLineNum = 131122;BA.debugLine="lblLang.Color = 0xFF1A1A35";
parent.mostCurrent._lbllang.setColor(((int)0xff1a1a35));
RDebugUtils.currentLine=131123;
 //BA.debugLineNum = 131123;BA.debugLine="bHeader.AddView(lblLang, 100%x - 65dip, 5dip, 55d";
_bheader.AddView((android.view.View)(parent.mostCurrent._lbllang.getObject()),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (65))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=131126;
 //BA.debugLineNum = 131126;BA.debugLine="CreateDetailPanel";
_createdetailpanel();
RDebugUtils.currentLine=131129;
 //BA.debugLineNum = 131129;BA.debugLine="MainUIReady = True";
parent._mainuiready = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=131130;
 //BA.debugLineNum = 131130;BA.debugLine="FillList(\"\")";
_filllist("");
RDebugUtils.currentLine=131133;
 //BA.debugLineNum = 131133;BA.debugLine="Sleep(2500) ' Let user enjoy the splash";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "main", "activity_create"),(int) (2500));
this.state = 5;
return;
case 5:
//C
this.state = -1;
;
RDebugUtils.currentLine=131134;
 //BA.debugLineNum = 131134;BA.debugLine="pnlSplash.SetLayoutAnimated(500, -100%x, 0, 100%x";
parent.mostCurrent._pnlsplash.SetLayoutAnimated((int) (500),(int) (-anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=131135;
 //BA.debugLineNum = 131135;BA.debugLine="svEntries.Visible = True";
parent.mostCurrent._sventries.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=131136;
 //BA.debugLineNum = 131136;BA.debugLine="bHeader.Visible = True";
_bheader.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=131137;
 //BA.debugLineNum = 131137;BA.debugLine="lblLang.BringToFront";
parent.mostCurrent._lbllang.BringToFront();
RDebugUtils.currentLine=131138;
 //BA.debugLineNum = 131138;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _createsplashscreen() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "createsplashscreen", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "createsplashscreen", null));}
anywheresoftware.b4a.objects.LabelWrapper _lblicon = null;
anywheresoftware.b4a.objects.B4XViewWrapper _bicon = null;
anywheresoftware.b4a.objects.LabelWrapper _lbltitle = null;
anywheresoftware.b4a.objects.B4XViewWrapper _btitle = null;
anywheresoftware.b4a.objects.LabelWrapper _lblload = null;
anywheresoftware.b4a.objects.B4XViewWrapper _bload = null;
RDebugUtils.currentLine=196608;
 //BA.debugLineNum = 196608;BA.debugLine="Sub CreateSplashScreen";
RDebugUtils.currentLine=196609;
 //BA.debugLineNum = 196609;BA.debugLine="pnlSplash = xui.CreatePanel(\"\")";
mostCurrent._pnlsplash = _xui.CreatePanel(processBA,"");
RDebugUtils.currentLine=196610;
 //BA.debugLineNum = 196610;BA.debugLine="pnlSplash.Color = 0xFF111128";
mostCurrent._pnlsplash.setColor(((int)0xff111128));
RDebugUtils.currentLine=196611;
 //BA.debugLineNum = 196611;BA.debugLine="Activity.AddView(pnlSplash, 0, 0, 100%x, 100%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._pnlsplash.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=196614;
 //BA.debugLineNum = 196614;BA.debugLine="Dim lblIcon As Label : lblIcon.Initialize(\"\")";
_lblicon = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=196614;
 //BA.debugLineNum = 196614;BA.debugLine="Dim lblIcon As Label : lblIcon.Initialize(\"\")";
_lblicon.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=196615;
 //BA.debugLineNum = 196615;BA.debugLine="Dim bIcon As B4XView = lblIcon";
_bicon = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bicon = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lblicon.getObject()));
RDebugUtils.currentLine=196616;
 //BA.debugLineNum = 196616;BA.debugLine="bIcon.Text = \"🏛️\"";
_bicon.setText(BA.ObjectToCharSequence("🏛️"));
RDebugUtils.currentLine=196617;
 //BA.debugLineNum = 196617;BA.debugLine="bIcon.TextSize = 80";
_bicon.setTextSize((float) (80));
RDebugUtils.currentLine=196618;
 //BA.debugLineNum = 196618;BA.debugLine="bIcon.SetTextAlignment(\"CENTER\", \"CENTER\")";
_bicon.SetTextAlignment("CENTER","CENTER");
RDebugUtils.currentLine=196619;
 //BA.debugLineNum = 196619;BA.debugLine="pnlSplash.AddView(bIcon, 0, 30%y, 100%x, 100dip)";
mostCurrent._pnlsplash.AddView((android.view.View)(_bicon.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (30),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)));
RDebugUtils.currentLine=196622;
 //BA.debugLineNum = 196622;BA.debugLine="Dim lblTitle As Label : lblTitle.Initialize(\"\")";
_lbltitle = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=196622;
 //BA.debugLineNum = 196622;BA.debugLine="Dim lblTitle As Label : lblTitle.Initialize(\"\")";
_lbltitle.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=196623;
 //BA.debugLineNum = 196623;BA.debugLine="Dim bTitle As B4XView = lblTitle";
_btitle = new anywheresoftware.b4a.objects.B4XViewWrapper();
_btitle = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lbltitle.getObject()));
RDebugUtils.currentLine=196624;
 //BA.debugLineNum = 196624;BA.debugLine="bTitle.Text = \"NEW MYTHOLOGY\"";
_btitle.setText(BA.ObjectToCharSequence("NEW MYTHOLOGY"));
RDebugUtils.currentLine=196625;
 //BA.debugLineNum = 196625;BA.debugLine="bTitle.TextColor = 0xFFEDD060";
_btitle.setTextColor(((int)0xffedd060));
RDebugUtils.currentLine=196626;
 //BA.debugLineNum = 196626;BA.debugLine="bTitle.TextSize = 28";
_btitle.setTextSize((float) (28));
RDebugUtils.currentLine=196627;
 //BA.debugLineNum = 196627;BA.debugLine="bTitle.Font = xui.CreateDefaultBoldFont(28)";
_btitle.setFont(_xui.CreateDefaultBoldFont((float) (28)));
RDebugUtils.currentLine=196628;
 //BA.debugLineNum = 196628;BA.debugLine="bTitle.SetTextAlignment(\"CENTER\", \"CENTER\")";
_btitle.SetTextAlignment("CENTER","CENTER");
RDebugUtils.currentLine=196629;
 //BA.debugLineNum = 196629;BA.debugLine="pnlSplash.AddView(bTitle, 0, 45%y, 100%x, 40dip)";
mostCurrent._pnlsplash.AddView((android.view.View)(_btitle.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (45),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=196632;
 //BA.debugLineNum = 196632;BA.debugLine="Dim lblLoad As Label : lblLoad.Initialize(\"\")";
_lblload = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=196632;
 //BA.debugLineNum = 196632;BA.debugLine="Dim lblLoad As Label : lblLoad.Initialize(\"\")";
_lblload.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=196633;
 //BA.debugLineNum = 196633;BA.debugLine="Dim bLoad As B4XView = lblLoad";
_bload = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bload = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lblload.getObject()));
RDebugUtils.currentLine=196634;
 //BA.debugLineNum = 196634;BA.debugLine="bLoad.Text = \"Loading myths...\"";
_bload.setText(BA.ObjectToCharSequence("Loading myths..."));
RDebugUtils.currentLine=196635;
 //BA.debugLineNum = 196635;BA.debugLine="bLoad.TextColor = 0xFFA89878";
_bload.setTextColor(((int)0xffa89878));
RDebugUtils.currentLine=196636;
 //BA.debugLineNum = 196636;BA.debugLine="bLoad.TextSize = 14";
_bload.setTextSize((float) (14));
RDebugUtils.currentLine=196637;
 //BA.debugLineNum = 196637;BA.debugLine="bLoad.SetTextAlignment(\"CENTER\", \"CENTER\")";
_bload.SetTextAlignment("CENTER","CENTER");
RDebugUtils.currentLine=196638;
 //BA.debugLineNum = 196638;BA.debugLine="pnlSplash.AddView(bLoad, 0, 80%y, 100%x, 30dip)";
mostCurrent._pnlsplash.AddView((android.view.View)(_bload.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (80),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
RDebugUtils.currentLine=196639;
 //BA.debugLineNum = 196639;BA.debugLine="End Sub";
return "";
}
public static String  _loaddata() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "loaddata", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "loaddata", null));}
String _smap = "";
anywheresoftware.b4a.objects.collections.JSONParser _jp = null;
String _sdata = "";
RDebugUtils.currentLine=458752;
 //BA.debugLineNum = 458752;BA.debugLine="Sub LoadData";
RDebugUtils.currentLine=458753;
 //BA.debugLineNum = 458753;BA.debugLine="Try";
try {RDebugUtils.currentLine=458755;
 //BA.debugLineNum = 458755;BA.debugLine="Dim sMap As String = File.ReadString(File.DirAss";
_smap = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"image_map.js");
RDebugUtils.currentLine=458756;
 //BA.debugLineNum = 458756;BA.debugLine="sMap = CleanJS(sMap, \"ENTRY_IMAGES\")";
_smap = _cleanjs(_smap,"ENTRY_IMAGES");
RDebugUtils.currentLine=458757;
 //BA.debugLineNum = 458757;BA.debugLine="Dim jp As JSONParser";
_jp = new anywheresoftware.b4a.objects.collections.JSONParser();
RDebugUtils.currentLine=458758;
 //BA.debugLineNum = 458758;BA.debugLine="jp.Initialize(sMap)";
_jp.Initialize(_smap);
RDebugUtils.currentLine=458759;
 //BA.debugLineNum = 458759;BA.debugLine="ImageMap = jp.NextObject";
_imagemap = _jp.NextObject();
RDebugUtils.currentLine=458760;
 //BA.debugLineNum = 458760;BA.debugLine="Log(\"Loaded \" & ImageMap.Size & \" image mappings";
anywheresoftware.b4a.keywords.Common.LogImpl("2458760","Loaded "+BA.NumberToString(_imagemap.getSize())+" image mappings.",0);
RDebugUtils.currentLine=458763;
 //BA.debugLineNum = 458763;BA.debugLine="Dim sData As String = File.ReadString(File.DirAs";
_sdata = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"mythology_data.js");
RDebugUtils.currentLine=458764;
 //BA.debugLineNum = 458764;BA.debugLine="sData = CleanJS(sData, \"MYTHOLOGY_DATA\")";
_sdata = _cleanjs(_sdata,"MYTHOLOGY_DATA");
RDebugUtils.currentLine=458765;
 //BA.debugLineNum = 458765;BA.debugLine="jp.Initialize(sData)";
_jp.Initialize(_sdata);
RDebugUtils.currentLine=458766;
 //BA.debugLineNum = 458766;BA.debugLine="MythologyData = jp.NextArray";
_mythologydata = _jp.NextArray();
RDebugUtils.currentLine=458767;
 //BA.debugLineNum = 458767;BA.debugLine="Log(\"Loaded \" & MythologyData.Size & \" mythology";
anywheresoftware.b4a.keywords.Common.LogImpl("2458767","Loaded "+BA.NumberToString(_mythologydata.getSize())+" mythology entries.",0);
 } 
       catch (Exception e14) {
			processBA.setLastException(e14);RDebugUtils.currentLine=458770;
 //BA.debugLineNum = 458770;BA.debugLine="Log(\"LoadData Error: \" & LastException.Message)";
anywheresoftware.b4a.keywords.Common.LogImpl("2458770","LoadData Error: "+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),0);
RDebugUtils.currentLine=458771;
 //BA.debugLineNum = 458771;BA.debugLine="MsgboxAsync(\"Error loading data: \" & LastExcepti";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Error loading data: "+anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage()),BA.ObjectToCharSequence("Error"),processBA);
 };
RDebugUtils.currentLine=458773;
 //BA.debugLineNum = 458773;BA.debugLine="End Sub";
return "";
}
public static String  _createdetailpanel() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "createdetailpanel", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "createdetailpanel", null));}
anywheresoftware.b4a.objects.PanelWrapper _pnlh = null;
anywheresoftware.b4a.objects.B4XViewWrapper _bh = null;
anywheresoftware.b4a.objects.PanelWrapper _pnlb = null;
anywheresoftware.b4a.objects.LabelWrapper _lback = null;
anywheresoftware.b4a.objects.B4XViewWrapper _bback = null;
anywheresoftware.b4a.objects.LabelWrapper _lname = null;
anywheresoftware.b4a.objects.ScrollViewWrapper _sv = null;
RDebugUtils.currentLine=327680;
 //BA.debugLineNum = 327680;BA.debugLine="Sub CreateDetailPanel";
RDebugUtils.currentLine=327681;
 //BA.debugLineNum = 327681;BA.debugLine="pnlDetail = xui.CreatePanel(\"pnlDetail\")";
mostCurrent._pnldetail = _xui.CreatePanel(processBA,"pnlDetail");
RDebugUtils.currentLine=327682;
 //BA.debugLineNum = 327682;BA.debugLine="pnlDetail.Color = 0xFF111128";
mostCurrent._pnldetail.setColor(((int)0xff111128));
RDebugUtils.currentLine=327683;
 //BA.debugLineNum = 327683;BA.debugLine="pnlDetail.Visible = False";
mostCurrent._pnldetail.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=327684;
 //BA.debugLineNum = 327684;BA.debugLine="Activity.AddView(pnlDetail, 0, 0, 100%x, 100%y)";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._pnldetail.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=327687;
 //BA.debugLineNum = 327687;BA.debugLine="Dim pnlH As Panel : pnlH.Initialize(\"\")";
_pnlh = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=327687;
 //BA.debugLineNum = 327687;BA.debugLine="Dim pnlH As Panel : pnlH.Initialize(\"\")";
_pnlh.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=327688;
 //BA.debugLineNum = 327688;BA.debugLine="Dim bH As B4XView = pnlH";
_bh = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bh = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_pnlh.getObject()));
RDebugUtils.currentLine=327689;
 //BA.debugLineNum = 327689;BA.debugLine="pnlDetail.AddView(bH, 0, 0, 100%x, 60dip)";
mostCurrent._pnldetail.AddView((android.view.View)(_bh.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
RDebugUtils.currentLine=327690;
 //BA.debugLineNum = 327690;BA.debugLine="bH.Color = 0xFF242445";
_bh.setColor(((int)0xff242445));
RDebugUtils.currentLine=327692;
 //BA.debugLineNum = 327692;BA.debugLine="Dim pnlB As Panel : pnlB.Initialize(\"btnBack\")";
_pnlb = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=327692;
 //BA.debugLineNum = 327692;BA.debugLine="Dim pnlB As Panel : pnlB.Initialize(\"btnBack\")";
_pnlb.Initialize(mostCurrent.activityBA,"btnBack");
RDebugUtils.currentLine=327693;
 //BA.debugLineNum = 327693;BA.debugLine="btnBack = pnlB";
mostCurrent._btnback = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_pnlb.getObject()));
RDebugUtils.currentLine=327694;
 //BA.debugLineNum = 327694;BA.debugLine="bH.AddView(btnBack, 5dip, 5dip, 50dip, 50dip)";
_bh.AddView((android.view.View)(mostCurrent._btnback.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=327696;
 //BA.debugLineNum = 327696;BA.debugLine="Dim lBack As Label : lBack.Initialize(\"btnBack\")";
_lback = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=327696;
 //BA.debugLineNum = 327696;BA.debugLine="Dim lBack As Label : lBack.Initialize(\"btnBack\")";
_lback.Initialize(mostCurrent.activityBA,"btnBack");
RDebugUtils.currentLine=327697;
 //BA.debugLineNum = 327697;BA.debugLine="Dim bBack As B4XView = lBack";
_bback = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bback = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lback.getObject()));
RDebugUtils.currentLine=327698;
 //BA.debugLineNum = 327698;BA.debugLine="bBack.Text = \"<\"";
_bback.setText(BA.ObjectToCharSequence("<"));
RDebugUtils.currentLine=327699;
 //BA.debugLineNum = 327699;BA.debugLine="bBack.TextColor = xui.Color_White";
_bback.setTextColor(_xui.Color_White);
RDebugUtils.currentLine=327700;
 //BA.debugLineNum = 327700;BA.debugLine="bBack.TextSize = 24";
_bback.setTextSize((float) (24));
RDebugUtils.currentLine=327701;
 //BA.debugLineNum = 327701;BA.debugLine="bBack.SetTextAlignment(\"CENTER\", \"CENTER\")";
_bback.SetTextAlignment("CENTER","CENTER");
RDebugUtils.currentLine=327702;
 //BA.debugLineNum = 327702;BA.debugLine="btnBack.AddView(bBack, 0, 0, 50dip, 50dip)";
mostCurrent._btnback.AddView((android.view.View)(_bback.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
RDebugUtils.currentLine=327704;
 //BA.debugLineNum = 327704;BA.debugLine="Dim lName As Label : lName.Initialize(\"\")";
_lname = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=327704;
 //BA.debugLineNum = 327704;BA.debugLine="Dim lName As Label : lName.Initialize(\"\")";
_lname.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=327705;
 //BA.debugLineNum = 327705;BA.debugLine="lblDetailName = lName";
mostCurrent._lbldetailname = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lname.getObject()));
RDebugUtils.currentLine=327706;
 //BA.debugLineNum = 327706;BA.debugLine="lblDetailName.TextColor = 0xFFEDD060";
mostCurrent._lbldetailname.setTextColor(((int)0xffedd060));
RDebugUtils.currentLine=327707;
 //BA.debugLineNum = 327707;BA.debugLine="lblDetailName.TextSize = 20";
mostCurrent._lbldetailname.setTextSize((float) (20));
RDebugUtils.currentLine=327708;
 //BA.debugLineNum = 327708;BA.debugLine="lblDetailName.Font = xui.CreateDefaultBoldFont(20";
mostCurrent._lbldetailname.setFont(_xui.CreateDefaultBoldFont((float) (20)));
RDebugUtils.currentLine=327709;
 //BA.debugLineNum = 327709;BA.debugLine="bH.AddView(lblDetailName, 60dip, 10dip, 100%x - 7";
_bh.AddView((android.view.View)(mostCurrent._lbldetailname.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=327712;
 //BA.debugLineNum = 327712;BA.debugLine="Dim sv As ScrollView : sv.Initialize(1000dip)";
_sv = new anywheresoftware.b4a.objects.ScrollViewWrapper();
RDebugUtils.currentLine=327712;
 //BA.debugLineNum = 327712;BA.debugLine="Dim sv As ScrollView : sv.Initialize(1000dip)";
_sv.Initialize(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1000)));
RDebugUtils.currentLine=327713;
 //BA.debugLineNum = 327713;BA.debugLine="svDetail = sv";
mostCurrent._svdetail = _sv;
RDebugUtils.currentLine=327714;
 //BA.debugLineNum = 327714;BA.debugLine="pnlDetail.AddView(svDetail, 0, 60dip, 100%x, 100%";
mostCurrent._pnldetail.AddView((android.view.View)(mostCurrent._svdetail.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60))));
RDebugUtils.currentLine=327715;
 //BA.debugLineNum = 327715;BA.debugLine="End Sub";
return "";
}
public static String  _filllist(String _query) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "filllist", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "filllist", new Object[] {_query}));}
int _cury = 0;
int _itemheight = 0;
anywheresoftware.b4a.objects.collections.Map _entry = null;
String _name = "";
String _desc = "";
anywheresoftware.b4a.objects.B4XViewWrapper _p = null;
RDebugUtils.currentLine=589824;
 //BA.debugLineNum = 589824;BA.debugLine="Sub FillList(Query As String)";
RDebugUtils.currentLine=589825;
 //BA.debugLineNum = 589825;BA.debugLine="If MainUIReady = False Then Return";
if (_mainuiready==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
RDebugUtils.currentLine=589826;
 //BA.debugLineNum = 589826;BA.debugLine="svEntries.Panel.RemoveAllViews";
mostCurrent._sventries.getPanel().RemoveAllViews();
RDebugUtils.currentLine=589827;
 //BA.debugLineNum = 589827;BA.debugLine="Dim curY As Int = 0";
_cury = (int) (0);
RDebugUtils.currentLine=589828;
 //BA.debugLineNum = 589828;BA.debugLine="Dim itemHeight As Int = 90dip";
_itemheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (90));
RDebugUtils.currentLine=589829;
 //BA.debugLineNum = 589829;BA.debugLine="Query = Query.ToLowerCase";
_query = _query.toLowerCase();
RDebugUtils.currentLine=589831;
 //BA.debugLineNum = 589831;BA.debugLine="For Each entry As Map In MythologyData";
_entry = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group6 = _mythologydata;
final int groupLen6 = group6.getSize()
;int index6 = 0;
;
for (; index6 < groupLen6;index6++){
_entry = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group6.Get(index6)));
RDebugUtils.currentLine=589832;
 //BA.debugLineNum = 589832;BA.debugLine="Dim name As String = entry.Get(\"name\")";
_name = BA.ObjectToString(_entry.Get((Object)("name")));
RDebugUtils.currentLine=589833;
 //BA.debugLineNum = 589833;BA.debugLine="Dim desc As String = entry.Get(\"description\")";
_desc = BA.ObjectToString(_entry.Get((Object)("description")));
RDebugUtils.currentLine=589835;
 //BA.debugLineNum = 589835;BA.debugLine="If Query = \"\" Or name.ToLowerCase.Contains(Query";
if ((_query).equals("") || _name.toLowerCase().contains(_query) || _desc.toLowerCase().contains(_query)) { 
RDebugUtils.currentLine=589836;
 //BA.debugLineNum = 589836;BA.debugLine="Dim p As B4XView = CreateItem(entry, svEntries.";
_p = new anywheresoftware.b4a.objects.B4XViewWrapper();
_p = _createitem(_entry,mostCurrent._sventries.getWidth(),_itemheight);
RDebugUtils.currentLine=589837;
 //BA.debugLineNum = 589837;BA.debugLine="svEntries.Panel.AddView(p, 0, curY, svEntries.W";
mostCurrent._sventries.getPanel().AddView((android.view.View)(_p.getObject()),(int) (0),_cury,mostCurrent._sventries.getWidth(),_itemheight);
RDebugUtils.currentLine=589838;
 //BA.debugLineNum = 589838;BA.debugLine="curY = curY + itemHeight";
_cury = (int) (_cury+_itemheight);
 };
 }
};
RDebugUtils.currentLine=589841;
 //BA.debugLineNum = 589841;BA.debugLine="svEntries.Panel.Height = curY";
mostCurrent._sventries.getPanel().setHeight(_cury);
RDebugUtils.currentLine=589842;
 //BA.debugLineNum = 589842;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_keypress", false))
	 {return ((Boolean) Debug.delegate(mostCurrent.activityBA, "activity_keypress", new Object[] {_keycode}));}
RDebugUtils.currentLine=917504;
 //BA.debugLineNum = 917504;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
RDebugUtils.currentLine=917505;
 //BA.debugLineNum = 917505;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
RDebugUtils.currentLine=917506;
 //BA.debugLineNum = 917506;BA.debugLine="If pnlDetail.Visible Then";
if (mostCurrent._pnldetail.getVisible()) { 
RDebugUtils.currentLine=917507;
 //BA.debugLineNum = 917507;BA.debugLine="pnlDetail.Visible = False";
mostCurrent._pnldetail.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917508;
 //BA.debugLineNum = 917508;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
RDebugUtils.currentLine=917512;
 //BA.debugLineNum = 917512;BA.debugLine="ConfirmExit";
_confirmexit();
RDebugUtils.currentLine=917513;
 //BA.debugLineNum = 917513;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
RDebugUtils.currentLine=917515;
 //BA.debugLineNum = 917515;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=917516;
 //BA.debugLineNum = 917516;BA.debugLine="End Sub";
return false;
}
public static void  _confirmexit() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "confirmexit", false))
	 {Debug.delegate(mostCurrent.activityBA, "confirmexit", null); return;}
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
RDebugUtils.currentModule="main";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=983041;
 //BA.debugLineNum = 983041;BA.debugLine="Msgbox2Async(\"Do you want to exit?\", \"Exit\", \"Yes";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Do you want to exit?"),BA.ObjectToCharSequence("Exit"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=983042;
 //BA.debugLineNum = 983042;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "main", "confirmexit"), null);
this.state = 5;
return;
case 5:
//C
this.state = 1;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=983043;
 //BA.debugLineNum = 983043;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=983044;
 //BA.debugLineNum = 983044;BA.debugLine="Activity.Finish";
parent.mostCurrent._activity.Finish();
 if (true) break;

case 4:
//C
this.state = -1;
;
RDebugUtils.currentLine=983046;
 //BA.debugLineNum = 983046;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _btnback_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnback_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnback_click", null));}
RDebugUtils.currentLine=393216;
 //BA.debugLineNum = 393216;BA.debugLine="Sub btnBack_Click";
RDebugUtils.currentLine=393217;
 //BA.debugLineNum = 393217;BA.debugLine="pnlDetail.Visible = False";
mostCurrent._pnldetail.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=393218;
 //BA.debugLineNum = 393218;BA.debugLine="End Sub";
return "";
}
public static String  _cleanjs(String _content,String _varname) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cleanjs", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cleanjs", new Object[] {_content,_varname}));}
int _idx1 = 0;
int _idx2 = 0;
int _startidx = 0;
RDebugUtils.currentLine=524288;
 //BA.debugLineNum = 524288;BA.debugLine="Sub CleanJS(Content As String, VarName As String)";
RDebugUtils.currentLine=524290;
 //BA.debugLineNum = 524290;BA.debugLine="Dim idx1 As Int = Content.IndexOf(\"[\")";
_idx1 = _content.indexOf("[");
RDebugUtils.currentLine=524291;
 //BA.debugLineNum = 524291;BA.debugLine="Dim idx2 As Int = Content.IndexOf(\"{\")";
_idx2 = _content.indexOf("{");
RDebugUtils.currentLine=524293;
 //BA.debugLineNum = 524293;BA.debugLine="Dim startIdx As Int = -1";
_startidx = (int) (-1);
RDebugUtils.currentLine=524294;
 //BA.debugLineNum = 524294;BA.debugLine="If idx1 > -1 And idx2 > -1 Then";
if (_idx1>-1 && _idx2>-1) { 
RDebugUtils.currentLine=524295;
 //BA.debugLineNum = 524295;BA.debugLine="startIdx = Min(idx1, idx2)";
_startidx = (int) (anywheresoftware.b4a.keywords.Common.Min(_idx1,_idx2));
 }else 
{RDebugUtils.currentLine=524296;
 //BA.debugLineNum = 524296;BA.debugLine="Else If idx1 > -1 Then";
if (_idx1>-1) { 
RDebugUtils.currentLine=524297;
 //BA.debugLineNum = 524297;BA.debugLine="startIdx = idx1";
_startidx = _idx1;
 }else {
RDebugUtils.currentLine=524299;
 //BA.debugLineNum = 524299;BA.debugLine="startIdx = idx2";
_startidx = _idx2;
 }}
;
RDebugUtils.currentLine=524302;
 //BA.debugLineNum = 524302;BA.debugLine="If startIdx > -1 Then";
if (_startidx>-1) { 
RDebugUtils.currentLine=524303;
 //BA.debugLineNum = 524303;BA.debugLine="Content = Content.SubString(startIdx)";
_content = _content.substring(_startidx);
 };
RDebugUtils.currentLine=524307;
 //BA.debugLineNum = 524307;BA.debugLine="Content = Content.Trim";
_content = _content.trim();
RDebugUtils.currentLine=524308;
 //BA.debugLineNum = 524308;BA.debugLine="If Content.EndsWith(\";\") Then";
if (_content.endsWith(";")) { 
RDebugUtils.currentLine=524309;
 //BA.debugLineNum = 524309;BA.debugLine="Content = Content.SubString2(0, Content.Length -";
_content = _content.substring((int) (0),(int) (_content.length()-1));
 };
RDebugUtils.currentLine=524311;
 //BA.debugLineNum = 524311;BA.debugLine="Return Content";
if (true) return _content;
RDebugUtils.currentLine=524312;
 //BA.debugLineNum = 524312;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.B4XViewWrapper  _createitem(anywheresoftware.b4a.objects.collections.Map _entry,int _width,int _height) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "createitem", false))
	 {return ((anywheresoftware.b4a.objects.B4XViewWrapper) Debug.delegate(mostCurrent.activityBA, "createitem", new Object[] {_entry,_width,_height}));}
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
anywheresoftware.b4a.objects.LabelWrapper _lbldesc = null;
anywheresoftware.b4a.objects.B4XViewWrapper _bdesc = null;
String _dtext = "";
RDebugUtils.currentLine=720896;
 //BA.debugLineNum = 720896;BA.debugLine="Sub CreateItem(Entry As Map, Width As Int, Height";
RDebugUtils.currentLine=720897;
 //BA.debugLineNum = 720897;BA.debugLine="Dim p As Panel : p.Initialize(\"pnlItem\")";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=720897;
 //BA.debugLineNum = 720897;BA.debugLine="Dim p As Panel : p.Initialize(\"pnlItem\")";
_p.Initialize(mostCurrent.activityBA,"pnlItem");
RDebugUtils.currentLine=720898;
 //BA.debugLineNum = 720898;BA.debugLine="Dim bP As B4XView = p";
_bp = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bp = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_p.getObject()));
RDebugUtils.currentLine=720899;
 //BA.debugLineNum = 720899;BA.debugLine="bP.Tag = Entry";
_bp.setTag((Object)(_entry.getObject()));
RDebugUtils.currentLine=720900;
 //BA.debugLineNum = 720900;BA.debugLine="bP.SetLayoutAnimated(0, 0, 0, Width, Height)";
_bp.SetLayoutAnimated((int) (0),(int) (0),(int) (0),_width,_height);
RDebugUtils.currentLine=720903;
 //BA.debugLineNum = 720903;BA.debugLine="Dim pBg As Panel : pBg.Initialize(\"pnlItem\")";
_pbg = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=720903;
 //BA.debugLineNum = 720903;BA.debugLine="Dim pBg As Panel : pBg.Initialize(\"pnlItem\")";
_pbg.Initialize(mostCurrent.activityBA,"pnlItem");
RDebugUtils.currentLine=720904;
 //BA.debugLineNum = 720904;BA.debugLine="Dim bBg As B4XView = pBg";
_bbg = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bbg = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_pbg.getObject()));
RDebugUtils.currentLine=720905;
 //BA.debugLineNum = 720905;BA.debugLine="bBg.Tag = Entry";
_bbg.setTag((Object)(_entry.getObject()));
RDebugUtils.currentLine=720906;
 //BA.debugLineNum = 720906;BA.debugLine="bP.AddView(bBg, 2dip, 2dip, Width - 4dip, Height";
_bp.AddView((android.view.View)(_bbg.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)),(int) (_width-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4))),(int) (_height-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (4))));
RDebugUtils.currentLine=720907;
 //BA.debugLineNum = 720907;BA.debugLine="bBg.Color = 0xFF1A1A35";
_bbg.setColor(((int)0xff1a1a35));
RDebugUtils.currentLine=720911;
 //BA.debugLineNum = 720911;BA.debugLine="Dim pnlImg As B4XView = xui.CreatePanel(\"\")";
_pnlimg = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnlimg = _xui.CreatePanel(processBA,"");
RDebugUtils.currentLine=720912;
 //BA.debugLineNum = 720912;BA.debugLine="pBg.AddView(pnlImg, 8dip, 8dip, 74dip, 74dip)";
_pbg.AddView((android.view.View)(_pnlimg.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (74)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (74)));
RDebugUtils.currentLine=720913;
 //BA.debugLineNum = 720913;BA.debugLine="pnlImg.Color = 0xFF242445";
_pnlimg.setColor(((int)0xff242445));
RDebugUtils.currentLine=720915;
 //BA.debugLineNum = 720915;BA.debugLine="Dim id As String = Entry.Get(\"id\")";
_id = BA.ObjectToString(_entry.Get((Object)("id")));
RDebugUtils.currentLine=720916;
 //BA.debugLineNum = 720916;BA.debugLine="If ImageMap.ContainsKey(id) Then";
if (_imagemap.ContainsKey((Object)(_id))) { 
RDebugUtils.currentLine=720917;
 //BA.debugLineNum = 720917;BA.debugLine="Dim imgs As List = ImageMap.Get(id)";
_imgs = new anywheresoftware.b4a.objects.collections.List();
_imgs = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_imagemap.Get((Object)(_id))));
RDebugUtils.currentLine=720918;
 //BA.debugLineNum = 720918;BA.debugLine="If imgs.Size > 0 Then";
if (_imgs.getSize()>0) { 
RDebugUtils.currentLine=720919;
 //BA.debugLineNum = 720919;BA.debugLine="Try";
try {RDebugUtils.currentLine=720920;
 //BA.debugLineNum = 720920;BA.debugLine="Dim imgName As String = imgs.Get(0)";
_imgname = BA.ObjectToString(_imgs.Get((int) (0)));
RDebugUtils.currentLine=720921;
 //BA.debugLineNum = 720921;BA.debugLine="Dim bmp As B4XBitmap = xui.LoadBitmapResize(Fi";
_bmp = new anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper();
_bmp = _xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"images/images/"+_imgname,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (74)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (74)),anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=720922;
 //BA.debugLineNum = 720922;BA.debugLine="Dim iv As ImageView : iv.Initialize(\"\")";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
RDebugUtils.currentLine=720922;
 //BA.debugLineNum = 720922;BA.debugLine="Dim iv As ImageView : iv.Initialize(\"\")";
_iv.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=720923;
 //BA.debugLineNum = 720923;BA.debugLine="Dim biv As B4XView = iv";
_biv = new anywheresoftware.b4a.objects.B4XViewWrapper();
_biv = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_iv.getObject()));
RDebugUtils.currentLine=720924;
 //BA.debugLineNum = 720924;BA.debugLine="biv.SetBitmap(bmp)";
_biv.SetBitmap((android.graphics.Bitmap)(_bmp.getObject()));
RDebugUtils.currentLine=720925;
 //BA.debugLineNum = 720925;BA.debugLine="pnlImg.AddView(biv, 0, 0, 74dip, 74dip)";
_pnlimg.AddView((android.view.View)(_biv.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (74)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (74)));
 } 
       catch (Exception e28) {
			processBA.setLastException(e28);RDebugUtils.currentLine=720927;
 //BA.debugLineNum = 720927;BA.debugLine="Log(\"Image Load Fail: \" & imgs.Get(0))";
anywheresoftware.b4a.keywords.Common.LogImpl("2720927","Image Load Fail: "+BA.ObjectToString(_imgs.Get((int) (0))),0);
 };
 };
 };
RDebugUtils.currentLine=720933;
 //BA.debugLineNum = 720933;BA.debugLine="Dim lblName As Label : lblName.Initialize(\"\")";
_lblname = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=720933;
 //BA.debugLineNum = 720933;BA.debugLine="Dim lblName As Label : lblName.Initialize(\"\")";
_lblname.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=720934;
 //BA.debugLineNum = 720934;BA.debugLine="Dim bName As B4XView = lblName";
_bname = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bname = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lblname.getObject()));
RDebugUtils.currentLine=720935;
 //BA.debugLineNum = 720935;BA.debugLine="bName.TextColor = 0xFFEDD060";
_bname.setTextColor(((int)0xffedd060));
RDebugUtils.currentLine=720936;
 //BA.debugLineNum = 720936;BA.debugLine="bName.TextSize = 18";
_bname.setTextSize((float) (18));
RDebugUtils.currentLine=720937;
 //BA.debugLineNum = 720937;BA.debugLine="bName.Font = xui.CreateDefaultBoldFont(18)";
_bname.setFont(_xui.CreateDefaultBoldFont((float) (18)));
RDebugUtils.currentLine=720938;
 //BA.debugLineNum = 720938;BA.debugLine="bName.Text = Entry.Get(\"name\")";
_bname.setText(BA.ObjectToCharSequence(_entry.Get((Object)("name"))));
RDebugUtils.currentLine=720939;
 //BA.debugLineNum = 720939;BA.debugLine="pBg.AddView(bName, 90dip, 10dip, Width - 100dip,";
_pbg.AddView((android.view.View)(_bname.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (90)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_width-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
RDebugUtils.currentLine=720942;
 //BA.debugLineNum = 720942;BA.debugLine="Dim lblDesc As Label : lblDesc.Initialize(\"\")";
_lbldesc = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=720942;
 //BA.debugLineNum = 720942;BA.debugLine="Dim lblDesc As Label : lblDesc.Initialize(\"\")";
_lbldesc.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=720943;
 //BA.debugLineNum = 720943;BA.debugLine="Dim bDesc As B4XView = lblDesc";
_bdesc = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bdesc = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lbldesc.getObject()));
RDebugUtils.currentLine=720944;
 //BA.debugLineNum = 720944;BA.debugLine="bDesc.TextColor = 0xFFA89878";
_bdesc.setTextColor(((int)0xffa89878));
RDebugUtils.currentLine=720945;
 //BA.debugLineNum = 720945;BA.debugLine="bDesc.TextSize = 13";
_bdesc.setTextSize((float) (13));
RDebugUtils.currentLine=720946;
 //BA.debugLineNum = 720946;BA.debugLine="Dim dText As String = Entry.Get(\"description\")";
_dtext = BA.ObjectToString(_entry.Get((Object)("description")));
RDebugUtils.currentLine=720947;
 //BA.debugLineNum = 720947;BA.debugLine="If dText.Length > 100 Then dText = dText.SubStrin";
if (_dtext.length()>100) { 
_dtext = _dtext.substring((int) (0),(int) (100)).replace(anywheresoftware.b4a.keywords.Common.CRLF," ")+"...";};
RDebugUtils.currentLine=720948;
 //BA.debugLineNum = 720948;BA.debugLine="bDesc.Text = dText";
_bdesc.setText(BA.ObjectToCharSequence(_dtext));
RDebugUtils.currentLine=720949;
 //BA.debugLineNum = 720949;BA.debugLine="pBg.AddView(bDesc, 90dip, 40dip, Width - 100dip,";
_pbg.AddView((android.view.View)(_bdesc.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (90)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),(int) (_width-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
RDebugUtils.currentLine=720951;
 //BA.debugLineNum = 720951;BA.debugLine="Return p";
if (true) return (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_p.getObject()));
RDebugUtils.currentLine=720952;
 //BA.debugLineNum = 720952;BA.debugLine="End Sub";
return null;
}
public static String  _lbllang_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "lbllang_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "lbllang_click", null));}
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Public Sub lblLang_Click";
RDebugUtils.currentLine=262145;
 //BA.debugLineNum = 262145;BA.debugLine="Log(\"Language Clicked\")";
anywheresoftware.b4a.keywords.Common.LogImpl("2262145","Language Clicked",0);
RDebugUtils.currentLine=262146;
 //BA.debugLineNum = 262146;BA.debugLine="If CurrentLanguage = \"el\" Then";
if ((_currentlanguage).equals("el")) { 
RDebugUtils.currentLine=262147;
 //BA.debugLineNum = 262147;BA.debugLine="CurrentLanguage = \"en\"";
_currentlanguage = "en";
RDebugUtils.currentLine=262148;
 //BA.debugLineNum = 262148;BA.debugLine="lblLang.Text = \"EN\"";
mostCurrent._lbllang.setText(BA.ObjectToCharSequence("EN"));
 }else {
RDebugUtils.currentLine=262150;
 //BA.debugLineNum = 262150;BA.debugLine="CurrentLanguage = \"el\"";
_currentlanguage = "el";
RDebugUtils.currentLine=262151;
 //BA.debugLineNum = 262151;BA.debugLine="lblLang.Text = \"EL\"";
mostCurrent._lbllang.setText(BA.ObjectToCharSequence("EL"));
 };
RDebugUtils.currentLine=262154;
 //BA.debugLineNum = 262154;BA.debugLine="FillList(txtSearch.Text)";
_filllist(mostCurrent._txtsearch.getText());
RDebugUtils.currentLine=262155;
 //BA.debugLineNum = 262155;BA.debugLine="End Sub";
return "";
}
public static String  _pnlitem_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pnlitem_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "pnlitem_click", null));}
anywheresoftware.b4a.objects.B4XViewWrapper _pnl = null;
RDebugUtils.currentLine=655360;
 //BA.debugLineNum = 655360;BA.debugLine="Sub pnlItem_Click";
RDebugUtils.currentLine=655361;
 //BA.debugLineNum = 655361;BA.debugLine="Dim pnl As B4XView = Sender";
_pnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnl = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
RDebugUtils.currentLine=655362;
 //BA.debugLineNum = 655362;BA.debugLine="ShowDetail(pnl.Tag)";
_showdetail((anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_pnl.getTag())));
RDebugUtils.currentLine=655363;
 //BA.debugLineNum = 655363;BA.debugLine="End Sub";
return "";
}
public static String  _showdetail(anywheresoftware.b4a.objects.collections.Map _entry) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showdetail", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showdetail", new Object[] {_entry}));}
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
anywheresoftware.b4a.objects.LabelWrapper _lbldesc = null;
anywheresoftware.b4a.objects.B4XViewWrapper _bdesc = null;
String _fulltext = "";
int _charcount = 0;
int _estimatedlines = 0;
int _textheight = 0;
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Sub ShowDetail(Entry As Map)";
RDebugUtils.currentLine=851969;
 //BA.debugLineNum = 851969;BA.debugLine="pnlDetail.Visible = True";
mostCurrent._pnldetail.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="pnlDetail.BringToFront";
mostCurrent._pnldetail.BringToFront();
RDebugUtils.currentLine=851971;
 //BA.debugLineNum = 851971;BA.debugLine="pnlDetail.SetLayoutAnimated(300, 0, 0, 100%x, 100";
mostCurrent._pnldetail.SetLayoutAnimated((int) (300),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=851973;
 //BA.debugLineNum = 851973;BA.debugLine="lblDetailName.Text = Entry.Get(\"name\")";
mostCurrent._lbldetailname.setText(BA.ObjectToCharSequence(_entry.Get((Object)("name"))));
RDebugUtils.currentLine=851976;
 //BA.debugLineNum = 851976;BA.debugLine="svDetail.Panel.RemoveAllViews";
mostCurrent._svdetail.getPanel().RemoveAllViews();
RDebugUtils.currentLine=851978;
 //BA.debugLineNum = 851978;BA.debugLine="Dim curY As Int = 15dip";
_cury = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15));
RDebugUtils.currentLine=851981;
 //BA.debugLineNum = 851981;BA.debugLine="Dim id As String = Entry.Get(\"id\")";
_id = BA.ObjectToString(_entry.Get((Object)("id")));
RDebugUtils.currentLine=851982;
 //BA.debugLineNum = 851982;BA.debugLine="If ImageMap.ContainsKey(id) Then";
if (_imagemap.ContainsKey((Object)(_id))) { 
RDebugUtils.currentLine=851983;
 //BA.debugLineNum = 851983;BA.debugLine="Dim imgs As List = ImageMap.Get(id)";
_imgs = new anywheresoftware.b4a.objects.collections.List();
_imgs = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_imagemap.Get((Object)(_id))));
RDebugUtils.currentLine=851984;
 //BA.debugLineNum = 851984;BA.debugLine="If imgs.Size > 0 Then";
if (_imgs.getSize()>0) { 
RDebugUtils.currentLine=851986;
 //BA.debugLineNum = 851986;BA.debugLine="Dim hsv As HorizontalScrollView : hsv.Initializ";
_hsv = new anywheresoftware.b4a.objects.HorizontalScrollViewWrapper();
RDebugUtils.currentLine=851986;
 //BA.debugLineNum = 851986;BA.debugLine="Dim hsv As HorizontalScrollView : hsv.Initializ";
_hsv.Initialize(mostCurrent.activityBA,(int) (0),"");
RDebugUtils.currentLine=851987;
 //BA.debugLineNum = 851987;BA.debugLine="svDetail.Panel.AddView(hsv, 0, curY, 100%x, 240";
mostCurrent._svdetail.getPanel().AddView((android.view.View)(_hsv.getObject()),(int) (0),_cury,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)));
RDebugUtils.currentLine=851989;
 //BA.debugLineNum = 851989;BA.debugLine="Dim curX As Int = 15dip";
_curx = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15));
RDebugUtils.currentLine=851990;
 //BA.debugLineNum = 851990;BA.debugLine="for each imgName as String in imgs";
{
final anywheresoftware.b4a.BA.IterableList group15 = _imgs;
final int groupLen15 = group15.getSize()
;int index15 = 0;
;
for (; index15 < groupLen15;index15++){
_imgname = BA.ObjectToString(group15.Get(index15));
RDebugUtils.currentLine=851991;
 //BA.debugLineNum = 851991;BA.debugLine="Dim pnlImg As B4XView = xui.CreatePanel(\"\")";
_pnlimg = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pnlimg = _xui.CreatePanel(processBA,"");
RDebugUtils.currentLine=851992;
 //BA.debugLineNum = 851992;BA.debugLine="hsv.Panel.AddView(pnlImg, curX, 10dip, 300dip,";
_hsv.getPanel().AddView((android.view.View)(_pnlimg.getObject()),_curx,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200)));
RDebugUtils.currentLine=851993;
 //BA.debugLineNum = 851993;BA.debugLine="pnlImg.Color = 0xFF242445";
_pnlimg.setColor(((int)0xff242445));
RDebugUtils.currentLine=851995;
 //BA.debugLineNum = 851995;BA.debugLine="Try";
try {RDebugUtils.currentLine=851996;
 //BA.debugLineNum = 851996;BA.debugLine="Dim bmp As B4XBitmap = xui.LoadBitmapResize(F";
_bmp = new anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper();
_bmp = _xui.LoadBitmapResize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"images/images/"+_imgname,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200)),anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=851997;
 //BA.debugLineNum = 851997;BA.debugLine="Dim iv As ImageView : iv.Initialize(\"\")";
_iv = new anywheresoftware.b4a.objects.ImageViewWrapper();
RDebugUtils.currentLine=851997;
 //BA.debugLineNum = 851997;BA.debugLine="Dim iv As ImageView : iv.Initialize(\"\")";
_iv.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=851998;
 //BA.debugLineNum = 851998;BA.debugLine="Dim biv As B4XView = iv";
_biv = new anywheresoftware.b4a.objects.B4XViewWrapper();
_biv = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_iv.getObject()));
RDebugUtils.currentLine=851999;
 //BA.debugLineNum = 851999;BA.debugLine="biv.SetBitmap(bmp)";
_biv.SetBitmap((android.graphics.Bitmap)(_bmp.getObject()));
RDebugUtils.currentLine=852000;
 //BA.debugLineNum = 852000;BA.debugLine="pnlImg.AddView(biv, 0, 0, 300dip, 200dip)";
_pnlimg.AddView((android.view.View)(_biv.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200)));
RDebugUtils.currentLine=852001;
 //BA.debugLineNum = 852001;BA.debugLine="curX = curX + 315dip";
_curx = (int) (_curx+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (315)));
 } 
       catch (Exception e28) {
			processBA.setLastException(e28);RDebugUtils.currentLine=852003;
 //BA.debugLineNum = 852003;BA.debugLine="Log(\"Detail Image Load Fail: \" & imgName)";
anywheresoftware.b4a.keywords.Common.LogImpl("2852003","Detail Image Load Fail: "+_imgname,0);
 };
 }
};
RDebugUtils.currentLine=852006;
 //BA.debugLineNum = 852006;BA.debugLine="hsv.Panel.Width = curX + 15dip";
_hsv.getPanel().setWidth((int) (_curx+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15))));
RDebugUtils.currentLine=852007;
 //BA.debugLineNum = 852007;BA.debugLine="curY = curY + 240dip";
_cury = (int) (_cury+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (240)));
 };
 };
RDebugUtils.currentLine=852012;
 //BA.debugLineNum = 852012;BA.debugLine="Dim lblHeader As Label : lblHeader.Initialize(\"\")";
_lblheader = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=852012;
 //BA.debugLineNum = 852012;BA.debugLine="Dim lblHeader As Label : lblHeader.Initialize(\"\")";
_lblheader.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=852013;
 //BA.debugLineNum = 852013;BA.debugLine="Dim bHeader As B4XView = lblHeader";
_bheader = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bheader = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lblheader.getObject()));
RDebugUtils.currentLine=852014;
 //BA.debugLineNum = 852014;BA.debugLine="bHeader.Text = \"ΠΕΡΙΓΡΑΦΗ\" ' Description in Greek";
_bheader.setText(BA.ObjectToCharSequence("ΠΕΡΙΓΡΑΦΗ"));
RDebugUtils.currentLine=852015;
 //BA.debugLineNum = 852015;BA.debugLine="bHeader.TextColor = 0xFFA89878";
_bheader.setTextColor(((int)0xffa89878));
RDebugUtils.currentLine=852016;
 //BA.debugLineNum = 852016;BA.debugLine="bHeader.TextSize = 14";
_bheader.setTextSize((float) (14));
RDebugUtils.currentLine=852017;
 //BA.debugLineNum = 852017;BA.debugLine="bHeader.Font = xui.CreateDefaultBoldFont(14)";
_bheader.setFont(_xui.CreateDefaultBoldFont((float) (14)));
RDebugUtils.currentLine=852018;
 //BA.debugLineNum = 852018;BA.debugLine="svDetail.Panel.AddView(bHeader, 15dip, curY, 100%";
mostCurrent._svdetail.getPanel().AddView((android.view.View)(_bheader.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)),_cury,(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
RDebugUtils.currentLine=852019;
 //BA.debugLineNum = 852019;BA.debugLine="curY = curY + 35dip";
_cury = (int) (_cury+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (35)));
RDebugUtils.currentLine=852022;
 //BA.debugLineNum = 852022;BA.debugLine="Dim lblDesc As Label : lblDesc.Initialize(\"\")";
_lbldesc = new anywheresoftware.b4a.objects.LabelWrapper();
RDebugUtils.currentLine=852022;
 //BA.debugLineNum = 852022;BA.debugLine="Dim lblDesc As Label : lblDesc.Initialize(\"\")";
_lbldesc.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=852023;
 //BA.debugLineNum = 852023;BA.debugLine="Dim bDesc As B4XView = lblDesc";
_bdesc = new anywheresoftware.b4a.objects.B4XViewWrapper();
_bdesc = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_lbldesc.getObject()));
RDebugUtils.currentLine=852024;
 //BA.debugLineNum = 852024;BA.debugLine="bDesc.TextColor = xui.Color_LightGray";
_bdesc.setTextColor(_xui.Color_LightGray);
RDebugUtils.currentLine=852025;
 //BA.debugLineNum = 852025;BA.debugLine="bDesc.TextSize = 17";
_bdesc.setTextSize((float) (17));
RDebugUtils.currentLine=852026;
 //BA.debugLineNum = 852026;BA.debugLine="Dim fullText As String = Entry.Get(\"description\")";
_fulltext = BA.ObjectToString(_entry.Get((Object)("description")));
RDebugUtils.currentLine=852027;
 //BA.debugLineNum = 852027;BA.debugLine="bDesc.Text = fullText";
_bdesc.setText(BA.ObjectToCharSequence(_fulltext));
RDebugUtils.currentLine=852030;
 //BA.debugLineNum = 852030;BA.debugLine="Dim charCount As Int = fullText.Length";
_charcount = _fulltext.length();
RDebugUtils.currentLine=852031;
 //BA.debugLineNum = 852031;BA.debugLine="Dim estimatedLines As Int = (charCount / 25) + 5";
_estimatedlines = (int) ((_charcount/(double)25)+5);
RDebugUtils.currentLine=852032;
 //BA.debugLineNum = 852032;BA.debugLine="Dim textHeight As Int = estimatedLines * 25dip";
_textheight = (int) (_estimatedlines*anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (25)));
RDebugUtils.currentLine=852034;
 //BA.debugLineNum = 852034;BA.debugLine="svDetail.Panel.AddView(bDesc, 15dip, curY, 100%x";
mostCurrent._svdetail.getPanel().AddView((android.view.View)(_bdesc.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)),_cury,(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30))),_textheight);
RDebugUtils.currentLine=852036;
 //BA.debugLineNum = 852036;BA.debugLine="svDetail.Panel.Height = curY + textHeight + 50dip";
mostCurrent._svdetail.getPanel().setHeight((int) (_cury+_textheight+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50))));
RDebugUtils.currentLine=852037;
 //BA.debugLineNum = 852037;BA.debugLine="End Sub";
return "";
}
public static String  _txtsearch_textchanged(String _old,String _new) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "txtsearch_textchanged", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "txtsearch_textchanged", new Object[] {_old,_new}));}
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="Sub txtSearch_TextChanged (Old As String, New As S";
RDebugUtils.currentLine=786433;
 //BA.debugLineNum = 786433;BA.debugLine="FillList(New)";
_filllist(_new);
RDebugUtils.currentLine=786434;
 //BA.debugLineNum = 786434;BA.debugLine="End Sub";
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