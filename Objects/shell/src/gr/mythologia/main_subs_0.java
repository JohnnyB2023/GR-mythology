package gr.mythologia;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static void  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,43);
if (RapidSub.canDelegate("activity_create")) { gr.mythologia.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime); return;}
ResumableSub_Activity_Create rsub = new ResumableSub_Activity_Create(null,_firsttime);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_Activity_Create extends BA.ResumableSub {
public ResumableSub_Activity_Create(gr.mythologia.main parent,RemoteObject _firsttime) {
this.parent = parent;
this._firsttime = _firsttime;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
gr.mythologia.main parent;
RemoteObject _firsttime;
RemoteObject _sv = RemoteObject.declareNull("anywheresoftware.b4a.objects.ScrollViewWrapper");
RemoteObject _pheader = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _bheader = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
RemoteObject _et = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
RemoteObject _bet = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
RemoteObject _llang = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,43);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 44;BA.debugLine="Log(\"Activity_Create Start\")";
Debug.ShouldStop(2048);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","2131073",RemoteObject.createImmutable("Activity_Create Start"),0);
 BA.debugLineNum = 45;BA.debugLine="MainUIReady = False";
Debug.ShouldStop(4096);
parent._mainuiready = parent.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 48;BA.debugLine="Activity.RemoveAllViews";
Debug.ShouldStop(32768);
parent.mostCurrent._activity.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 49;BA.debugLine="Activity.Color = 0xFF111128";
Debug.ShouldStop(65536);
parent.mostCurrent._activity.runVoidMethod ("setColor",BA.numberCast(int.class, ((int)0xff111128)));
 BA.debugLineNum = 52;BA.debugLine="CreateSplashScreen";
Debug.ShouldStop(524288);
_createsplashscreen();
 BA.debugLineNum = 55;BA.debugLine="If FirstTime Then";
Debug.ShouldStop(4194304);
if (true) break;

case 1:
//if
this.state = 4;
if (_firsttime.<Boolean>get().booleanValue()) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 BA.debugLineNum = 56;BA.debugLine="LoadData";
Debug.ShouldStop(8388608);
_loaddata();
 if (true) break;

case 4:
//C
this.state = -1;
;
 BA.debugLineNum = 61;BA.debugLine="Dim sv As ScrollView : sv.Initialize(0)";
Debug.ShouldStop(268435456);
_sv = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");Debug.locals.put("sv", _sv);
 BA.debugLineNum = 61;BA.debugLine="Dim sv As ScrollView : sv.Initialize(0)";
Debug.ShouldStop(268435456);
_sv.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(BA.numberCast(int.class, 0)));
 BA.debugLineNum = 62;BA.debugLine="svEntries = sv";
Debug.ShouldStop(536870912);
parent.mostCurrent._sventries = _sv;
 BA.debugLineNum = 63;BA.debugLine="Activity.AddView(svEntries, 0, 60dip, 100%x, 100%";
Debug.ShouldStop(1073741824);
parent.mostCurrent._activity.runVoidMethod ("AddView",(Object)((parent.mostCurrent._sventries.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))),(Object)(parent.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(RemoteObject.solve(new RemoteObject[] {parent.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA),parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))}, "-",1, 1)));
 BA.debugLineNum = 64;BA.debugLine="svEntries.Visible = False ' Hide until splash end";
Debug.ShouldStop(-2147483648);
parent.mostCurrent._sventries.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 67;BA.debugLine="Dim pHeader As Panel : pHeader.Initialize(\"\")";
Debug.ShouldStop(4);
_pheader = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pHeader", _pheader);
 BA.debugLineNum = 67;BA.debugLine="Dim pHeader As Panel : pHeader.Initialize(\"\")";
Debug.ShouldStop(4);
_pheader.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 68;BA.debugLine="Dim bHeader As B4XView = pHeader";
Debug.ShouldStop(8);
_bheader = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_bheader = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _pheader.getObject());Debug.locals.put("bHeader", _bheader);Debug.locals.put("bHeader", _bheader);
 BA.debugLineNum = 69;BA.debugLine="Activity.AddView(bHeader, 0, 0, 100%x, 60dip)";
Debug.ShouldStop(16);
parent.mostCurrent._activity.runVoidMethod ("AddView",(Object)((_bheader.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(parent.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))));
 BA.debugLineNum = 70;BA.debugLine="bHeader.Color = 0xFF242445";
Debug.ShouldStop(32);
_bheader.runMethod(true,"setColor",BA.numberCast(int.class, ((int)0xff242445)));
 BA.debugLineNum = 71;BA.debugLine="bHeader.Visible = False";
Debug.ShouldStop(64);
_bheader.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 74;BA.debugLine="Dim et As EditText : et.Initialize(\"txtSearch\")";
Debug.ShouldStop(512);
_et = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");Debug.locals.put("et", _et);
 BA.debugLineNum = 74;BA.debugLine="Dim et As EditText : et.Initialize(\"txtSearch\")";
Debug.ShouldStop(512);
_et.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("txtSearch")));
 BA.debugLineNum = 75;BA.debugLine="et.Hint = \"Search myths...\"";
Debug.ShouldStop(1024);
_et.runMethod(true,"setHint",BA.ObjectToString("Search myths..."));
 BA.debugLineNum = 76;BA.debugLine="et.HintColor = 0xFFA89878";
Debug.ShouldStop(2048);
_et.runMethod(true,"setHintColor",BA.numberCast(int.class, ((int)0xffa89878)));
 BA.debugLineNum = 77;BA.debugLine="et.TextColor = xui.Color_White";
Debug.ShouldStop(4096);
_et.runMethod(true,"setTextColor",parent._xui.getField(true,"Color_White"));
 BA.debugLineNum = 78;BA.debugLine="et.TextSize = 16";
Debug.ShouldStop(8192);
_et.runMethod(true,"setTextSize",BA.numberCast(float.class, 16));
 BA.debugLineNum = 79;BA.debugLine="et.Color = 0xFF1A1A35";
Debug.ShouldStop(16384);
_et.runVoidMethod ("setColor",BA.numberCast(int.class, ((int)0xff1a1a35)));
 BA.debugLineNum = 80;BA.debugLine="txtSearch = et";
Debug.ShouldStop(32768);
parent.mostCurrent._txtsearch = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _et.getObject());
 BA.debugLineNum = 81;BA.debugLine="Dim bET As B4XView = et";
Debug.ShouldStop(65536);
_bet = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_bet = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _et.getObject());Debug.locals.put("bET", _bet);Debug.locals.put("bET", _bet);
 BA.debugLineNum = 82;BA.debugLine="bET.SetTextAlignment(\"CENTER\", \"LEFT\")";
Debug.ShouldStop(131072);
_bet.runVoidMethod ("SetTextAlignment",(Object)(BA.ObjectToString("CENTER")),(Object)(RemoteObject.createImmutable("LEFT")));
 BA.debugLineNum = 83;BA.debugLine="bHeader.AddView(txtSearch, 10dip, 5dip, 100%x - 8";
Debug.ShouldStop(262144);
_bheader.runVoidMethod ("AddView",(Object)((parent.mostCurrent._txtsearch.getObject())),(Object)(parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))),(Object)(RemoteObject.solve(new RemoteObject[] {parent.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA),parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 80)))}, "-",1, 1)),(Object)(parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
 BA.debugLineNum = 86;BA.debugLine="Dim lLang As Label : lLang.Initialize(\"lblLang\")";
Debug.ShouldStop(2097152);
_llang = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lLang", _llang);
 BA.debugLineNum = 86;BA.debugLine="Dim lLang As Label : lLang.Initialize(\"lblLang\")";
Debug.ShouldStop(2097152);
_llang.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("lblLang")));
 BA.debugLineNum = 87;BA.debugLine="lblLang = lLang";
Debug.ShouldStop(4194304);
parent.mostCurrent._lbllang = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _llang.getObject());
 BA.debugLineNum = 88;BA.debugLine="lblLang.Text = \"EL\"";
Debug.ShouldStop(8388608);
parent.mostCurrent._lbllang.runMethod(true,"setText",BA.ObjectToCharSequence("EL"));
 BA.debugLineNum = 89;BA.debugLine="lblLang.TextColor = 0xFFEDD060";
Debug.ShouldStop(16777216);
parent.mostCurrent._lbllang.runMethod(true,"setTextColor",BA.numberCast(int.class, ((int)0xffedd060)));
 BA.debugLineNum = 90;BA.debugLine="lblLang.TextSize = 18";
Debug.ShouldStop(33554432);
parent.mostCurrent._lbllang.runMethod(true,"setTextSize",BA.numberCast(float.class, 18));
 BA.debugLineNum = 91;BA.debugLine="lblLang.Font = xui.CreateDefaultBoldFont(18)";
Debug.ShouldStop(67108864);
parent.mostCurrent._lbllang.runMethod(false,"setFont",parent._xui.runMethod(false,"CreateDefaultBoldFont",(Object)(BA.numberCast(float.class, 18))));
 BA.debugLineNum = 92;BA.debugLine="lblLang.SetTextAlignment(\"CENTER\", \"CENTER\")";
Debug.ShouldStop(134217728);
parent.mostCurrent._lbllang.runVoidMethod ("SetTextAlignment",(Object)(BA.ObjectToString("CENTER")),(Object)(RemoteObject.createImmutable("CENTER")));
 BA.debugLineNum = 93;BA.debugLine="lblLang.Color = 0xFF1A1A35";
Debug.ShouldStop(268435456);
parent.mostCurrent._lbllang.runMethod(true,"setColor",BA.numberCast(int.class, ((int)0xff1a1a35)));
 BA.debugLineNum = 94;BA.debugLine="bHeader.AddView(lblLang, 100%x - 65dip, 5dip, 55d";
Debug.ShouldStop(536870912);
_bheader.runVoidMethod ("AddView",(Object)((parent.mostCurrent._lbllang.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {parent.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA),parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 65)))}, "-",1, 1)),(Object)(parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))),(Object)(parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 55)))),(Object)(parent.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
 BA.debugLineNum = 97;BA.debugLine="CreateDetailPanel";
Debug.ShouldStop(1);
_createdetailpanel();
 BA.debugLineNum = 100;BA.debugLine="MainUIReady = True";
Debug.ShouldStop(8);
parent._mainuiready = parent.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 101;BA.debugLine="FillList(\"\")";
Debug.ShouldStop(16);
_filllist(RemoteObject.createImmutable(""));
 BA.debugLineNum = 104;BA.debugLine="Sleep(2500) ' Let user enjoy the splash";
Debug.ShouldStop(128);
parent.mostCurrent.__c.runVoidMethod ("Sleep",main.mostCurrent.activityBA,anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "main", "activity_create"),BA.numberCast(int.class, 2500));
this.state = 5;
return;
case 5:
//C
this.state = -1;
;
 BA.debugLineNum = 105;BA.debugLine="pnlSplash.SetLayoutAnimated(500, -100%x, 0, 100%x";
Debug.ShouldStop(256);
parent.mostCurrent._pnlsplash.runVoidMethod ("SetLayoutAnimated",(Object)(BA.numberCast(int.class, 500)),(Object)(BA.numberCast(int.class, -(double) (0 + parent.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA).<Integer>get().intValue()))),(Object)(BA.numberCast(int.class, 0)),(Object)(parent.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(parent.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 106;BA.debugLine="svEntries.Visible = True";
Debug.ShouldStop(512);
parent.mostCurrent._sventries.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 107;BA.debugLine="bHeader.Visible = True";
Debug.ShouldStop(1024);
_bheader.runMethod(true,"setVisible",parent.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 108;BA.debugLine="lblLang.BringToFront";
Debug.ShouldStop(2048);
parent.mostCurrent._lbllang.runVoidMethod ("BringToFront");
 BA.debugLineNum = 109;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static RemoteObject  _activity_keypress(RemoteObject _keycode) throws Exception{
try {
		Debug.PushSubsStack("Activity_KeyPress (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,406);
if (RapidSub.canDelegate("activity_keypress")) { return gr.mythologia.main.remoteMe.runUserSub(false, "main","activity_keypress", _keycode);}
Debug.locals.put("KeyCode", _keycode);
 BA.debugLineNum = 406;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 407;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
Debug.ShouldStop(4194304);
if (RemoteObject.solveBoolean("=",_keycode,BA.numberCast(double.class, main.mostCurrent.__c.getField(false,"KeyCodes").getField(true,"KEYCODE_BACK")))) { 
 BA.debugLineNum = 408;BA.debugLine="If pnlDetail.Visible Then";
Debug.ShouldStop(8388608);
if (main.mostCurrent._pnldetail.runMethod(true,"getVisible").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 409;BA.debugLine="pnlDetail.Visible = False";
Debug.ShouldStop(16777216);
main.mostCurrent._pnldetail.runMethod(true,"setVisible",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 410;BA.debugLine="Return True";
Debug.ShouldStop(33554432);
if (true) return main.mostCurrent.__c.getField(true,"True");
 };
 BA.debugLineNum = 414;BA.debugLine="ConfirmExit";
Debug.ShouldStop(536870912);
_confirmexit();
 BA.debugLineNum = 415;BA.debugLine="Return True";
Debug.ShouldStop(1073741824);
if (true) return main.mostCurrent.__c.getField(true,"True");
 };
 BA.debugLineNum = 417;BA.debugLine="Return False";
Debug.ShouldStop(1);
if (true) return main.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 418;BA.debugLine="End Sub";
Debug.ShouldStop(2);
return RemoteObject.createImmutable(false);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnback_click() throws Exception{
try {
		Debug.PushSubsStack("btnBack_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,194);
if (RapidSub.canDelegate("btnback_click")) { return gr.mythologia.main.remoteMe.runUserSub(false, "main","btnback_click");}
 BA.debugLineNum = 194;BA.debugLine="Sub btnBack_Click";
Debug.ShouldStop(2);
 BA.debugLineNum = 195;BA.debugLine="pnlDetail.Visible = False";
Debug.ShouldStop(4);
main.mostCurrent._pnldetail.runMethod(true,"setVisible",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 196;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _cleanjs(RemoteObject _content,RemoteObject _varname) throws Exception{
try {
		Debug.PushSubsStack("CleanJS (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,222);
if (RapidSub.canDelegate("cleanjs")) { return gr.mythologia.main.remoteMe.runUserSub(false, "main","cleanjs", _content, _varname);}
RemoteObject _idx1 = RemoteObject.createImmutable(0);
RemoteObject _idx2 = RemoteObject.createImmutable(0);
RemoteObject _startidx = RemoteObject.createImmutable(0);
Debug.locals.put("Content", _content);
Debug.locals.put("VarName", _varname);
 BA.debugLineNum = 222;BA.debugLine="Sub CleanJS(Content As String, VarName As String)";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 224;BA.debugLine="Dim idx1 As Int = Content.IndexOf(\"[\")";
Debug.ShouldStop(-2147483648);
_idx1 = _content.runMethod(true,"indexOf",(Object)(RemoteObject.createImmutable("[")));Debug.locals.put("idx1", _idx1);Debug.locals.put("idx1", _idx1);
 BA.debugLineNum = 225;BA.debugLine="Dim idx2 As Int = Content.IndexOf(\"{\")";
Debug.ShouldStop(1);
_idx2 = _content.runMethod(true,"indexOf",(Object)(RemoteObject.createImmutable("{")));Debug.locals.put("idx2", _idx2);Debug.locals.put("idx2", _idx2);
 BA.debugLineNum = 227;BA.debugLine="Dim startIdx As Int = -1";
Debug.ShouldStop(4);
_startidx = BA.numberCast(int.class, -(double) (0 + 1));Debug.locals.put("startIdx", _startidx);Debug.locals.put("startIdx", _startidx);
 BA.debugLineNum = 228;BA.debugLine="If idx1 > -1 And idx2 > -1 Then";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean(">",_idx1,BA.numberCast(double.class, -(double) (0 + 1))) && RemoteObject.solveBoolean(">",_idx2,BA.numberCast(double.class, -(double) (0 + 1)))) { 
 BA.debugLineNum = 229;BA.debugLine="startIdx = Min(idx1, idx2)";
Debug.ShouldStop(16);
_startidx = BA.numberCast(int.class, main.mostCurrent.__c.runMethod(true,"Min",(Object)(BA.numberCast(double.class, _idx1)),(Object)(BA.numberCast(double.class, _idx2))));Debug.locals.put("startIdx", _startidx);
 }else 
{ BA.debugLineNum = 230;BA.debugLine="Else If idx1 > -1 Then";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean(">",_idx1,BA.numberCast(double.class, -(double) (0 + 1)))) { 
 BA.debugLineNum = 231;BA.debugLine="startIdx = idx1";
Debug.ShouldStop(64);
_startidx = _idx1;Debug.locals.put("startIdx", _startidx);
 }else {
 BA.debugLineNum = 233;BA.debugLine="startIdx = idx2";
Debug.ShouldStop(256);
_startidx = _idx2;Debug.locals.put("startIdx", _startidx);
 }}
;
 BA.debugLineNum = 236;BA.debugLine="If startIdx > -1 Then";
Debug.ShouldStop(2048);
if (RemoteObject.solveBoolean(">",_startidx,BA.numberCast(double.class, -(double) (0 + 1)))) { 
 BA.debugLineNum = 237;BA.debugLine="Content = Content.SubString(startIdx)";
Debug.ShouldStop(4096);
_content = _content.runMethod(true,"substring",(Object)(_startidx));Debug.locals.put("Content", _content);
 };
 BA.debugLineNum = 241;BA.debugLine="Content = Content.Trim";
Debug.ShouldStop(65536);
_content = _content.runMethod(true,"trim");Debug.locals.put("Content", _content);
 BA.debugLineNum = 242;BA.debugLine="If Content.EndsWith(\";\") Then";
Debug.ShouldStop(131072);
if (_content.runMethod(true,"endsWith",(Object)(RemoteObject.createImmutable(";"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 243;BA.debugLine="Content = Content.SubString2(0, Content.Length -";
Debug.ShouldStop(262144);
_content = _content.runMethod(true,"substring",(Object)(BA.numberCast(int.class, 0)),(Object)(RemoteObject.solve(new RemoteObject[] {_content.runMethod(true,"length"),RemoteObject.createImmutable(1)}, "-",1, 1)));Debug.locals.put("Content", _content);
 };
 BA.debugLineNum = 245;BA.debugLine="Return Content";
Debug.ShouldStop(1048576);
if (true) return _content;
 BA.debugLineNum = 246;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static void  _confirmexit() throws Exception{
try {
		Debug.PushSubsStack("ConfirmExit (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,420);
if (RapidSub.canDelegate("confirmexit")) { gr.mythologia.main.remoteMe.runUserSub(false, "main","confirmexit"); return;}
ResumableSub_ConfirmExit rsub = new ResumableSub_ConfirmExit(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_ConfirmExit extends BA.ResumableSub {
public ResumableSub_ConfirmExit(gr.mythologia.main parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
gr.mythologia.main parent;
RemoteObject _result = RemoteObject.createImmutable(0);

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("ConfirmExit (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,420);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 421;BA.debugLine="Msgbox2Async(\"Do you want to exit?\", \"Exit\", \"Yes";
Debug.ShouldStop(16);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Do you want to exit?")),(Object)(BA.ObjectToCharSequence("Exit")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),main.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 422;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(32);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", main.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "main", "confirmexit"), null);
this.state = 5;
return;
case 5:
//C
this.state = 1;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 423;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
Debug.ShouldStop(64);
if (true) break;

case 1:
//if
this.state = 4;
if (RemoteObject.solveBoolean("=",_result,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 BA.debugLineNum = 424;BA.debugLine="Activity.Finish";
Debug.ShouldStop(128);
parent.mostCurrent._activity.runVoidMethod ("Finish");
 if (true) break;

case 4:
//C
this.state = -1;
;
 BA.debugLineNum = 426;BA.debugLine="End Sub";
Debug.ShouldStop(512);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _msgbox_result(RemoteObject _result) throws Exception{
}
public static RemoteObject  _createdetailpanel() throws Exception{
try {
		Debug.PushSubsStack("CreateDetailPanel (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,157);
if (RapidSub.canDelegate("createdetailpanel")) { return gr.mythologia.main.remoteMe.runUserSub(false, "main","createdetailpanel");}
RemoteObject _pnlh = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _bh = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
RemoteObject _pnlb = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _lback = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _bback = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
RemoteObject _lname = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _sv = RemoteObject.declareNull("anywheresoftware.b4a.objects.ScrollViewWrapper");
 BA.debugLineNum = 157;BA.debugLine="Sub CreateDetailPanel";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 158;BA.debugLine="pnlDetail = xui.CreatePanel(\"pnlDetail\")";
Debug.ShouldStop(536870912);
main.mostCurrent._pnldetail = main._xui.runMethod(false,"CreatePanel",main.processBA,(Object)(RemoteObject.createImmutable("pnlDetail")));
 BA.debugLineNum = 159;BA.debugLine="pnlDetail.Color = 0xFF111128";
Debug.ShouldStop(1073741824);
main.mostCurrent._pnldetail.runMethod(true,"setColor",BA.numberCast(int.class, ((int)0xff111128)));
 BA.debugLineNum = 160;BA.debugLine="pnlDetail.Visible = False";
Debug.ShouldStop(-2147483648);
main.mostCurrent._pnldetail.runMethod(true,"setVisible",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 161;BA.debugLine="Activity.AddView(pnlDetail, 0, 0, 100%x, 100%y)";
Debug.ShouldStop(1);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((main.mostCurrent._pnldetail.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 164;BA.debugLine="Dim pnlH As Panel : pnlH.Initialize(\"\")";
Debug.ShouldStop(8);
_pnlh = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pnlH", _pnlh);
 BA.debugLineNum = 164;BA.debugLine="Dim pnlH As Panel : pnlH.Initialize(\"\")";
Debug.ShouldStop(8);
_pnlh.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 165;BA.debugLine="Dim bH As B4XView = pnlH";
Debug.ShouldStop(16);
_bh = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_bh = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _pnlh.getObject());Debug.locals.put("bH", _bh);Debug.locals.put("bH", _bh);
 BA.debugLineNum = 166;BA.debugLine="pnlDetail.AddView(bH, 0, 0, 100%x, 60dip)";
Debug.ShouldStop(32);
main.mostCurrent._pnldetail.runVoidMethod ("AddView",(Object)((_bh.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))));
 BA.debugLineNum = 167;BA.debugLine="bH.Color = 0xFF242445";
Debug.ShouldStop(64);
_bh.runMethod(true,"setColor",BA.numberCast(int.class, ((int)0xff242445)));
 BA.debugLineNum = 169;BA.debugLine="Dim pnlB As Panel : pnlB.Initialize(\"btnBack\")";
Debug.ShouldStop(256);
_pnlb = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pnlB", _pnlb);
 BA.debugLineNum = 169;BA.debugLine="Dim pnlB As Panel : pnlB.Initialize(\"btnBack\")";
Debug.ShouldStop(256);
_pnlb.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnBack")));
 BA.debugLineNum = 170;BA.debugLine="btnBack = pnlB";
Debug.ShouldStop(512);
main.mostCurrent._btnback = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _pnlb.getObject());
 BA.debugLineNum = 171;BA.debugLine="bH.AddView(btnBack, 5dip, 5dip, 50dip, 50dip)";
Debug.ShouldStop(1024);
_bh.runVoidMethod ("AddView",(Object)((main.mostCurrent._btnback.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
 BA.debugLineNum = 173;BA.debugLine="Dim lBack As Label : lBack.Initialize(\"btnBack\")";
Debug.ShouldStop(4096);
_lback = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lBack", _lback);
 BA.debugLineNum = 173;BA.debugLine="Dim lBack As Label : lBack.Initialize(\"btnBack\")";
Debug.ShouldStop(4096);
_lback.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnBack")));
 BA.debugLineNum = 174;BA.debugLine="Dim bBack As B4XView = lBack";
Debug.ShouldStop(8192);
_bback = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_bback = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _lback.getObject());Debug.locals.put("bBack", _bback);Debug.locals.put("bBack", _bback);
 BA.debugLineNum = 175;BA.debugLine="bBack.Text = \"<\"";
Debug.ShouldStop(16384);
_bback.runMethod(true,"setText",BA.ObjectToCharSequence("<"));
 BA.debugLineNum = 176;BA.debugLine="bBack.TextColor = xui.Color_White";
Debug.ShouldStop(32768);
_bback.runMethod(true,"setTextColor",main._xui.getField(true,"Color_White"));
 BA.debugLineNum = 177;BA.debugLine="bBack.TextSize = 24";
Debug.ShouldStop(65536);
_bback.runMethod(true,"setTextSize",BA.numberCast(float.class, 24));
 BA.debugLineNum = 178;BA.debugLine="bBack.SetTextAlignment(\"CENTER\", \"CENTER\")";
Debug.ShouldStop(131072);
_bback.runVoidMethod ("SetTextAlignment",(Object)(BA.ObjectToString("CENTER")),(Object)(RemoteObject.createImmutable("CENTER")));
 BA.debugLineNum = 179;BA.debugLine="btnBack.AddView(bBack, 0, 0, 50dip, 50dip)";
Debug.ShouldStop(262144);
main.mostCurrent._btnback.runVoidMethod ("AddView",(Object)((_bback.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
 BA.debugLineNum = 181;BA.debugLine="Dim lName As Label : lName.Initialize(\"\")";
Debug.ShouldStop(1048576);
_lname = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lName", _lname);
 BA.debugLineNum = 181;BA.debugLine="Dim lName As Label : lName.Initialize(\"\")";
Debug.ShouldStop(1048576);
_lname.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 182;BA.debugLine="lblDetailName = lName";
Debug.ShouldStop(2097152);
main.mostCurrent._lbldetailname = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _lname.getObject());
 BA.debugLineNum = 183;BA.debugLine="lblDetailName.TextColor = 0xFFEDD060";
Debug.ShouldStop(4194304);
main.mostCurrent._lbldetailname.runMethod(true,"setTextColor",BA.numberCast(int.class, ((int)0xffedd060)));
 BA.debugLineNum = 184;BA.debugLine="lblDetailName.TextSize = 20";
Debug.ShouldStop(8388608);
main.mostCurrent._lbldetailname.runMethod(true,"setTextSize",BA.numberCast(float.class, 20));
 BA.debugLineNum = 185;BA.debugLine="lblDetailName.Font = xui.CreateDefaultBoldFont(20";
Debug.ShouldStop(16777216);
main.mostCurrent._lbldetailname.runMethod(false,"setFont",main._xui.runMethod(false,"CreateDefaultBoldFont",(Object)(BA.numberCast(float.class, 20))));
 BA.debugLineNum = 186;BA.debugLine="bH.AddView(lblDetailName, 60dip, 10dip, 100%x - 7";
Debug.ShouldStop(33554432);
_bh.runVoidMethod ("AddView",(Object)((main.mostCurrent._lbldetailname.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(RemoteObject.solve(new RemoteObject[] {main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 70)))}, "-",1, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 189;BA.debugLine="Dim sv As ScrollView : sv.Initialize(1000dip)";
Debug.ShouldStop(268435456);
_sv = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");Debug.locals.put("sv", _sv);
 BA.debugLineNum = 189;BA.debugLine="Dim sv As ScrollView : sv.Initialize(1000dip)";
Debug.ShouldStop(268435456);
_sv.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 1000)))));
 BA.debugLineNum = 190;BA.debugLine="svDetail = sv";
Debug.ShouldStop(536870912);
main.mostCurrent._svdetail = _sv;
 BA.debugLineNum = 191;BA.debugLine="pnlDetail.AddView(svDetail, 0, 60dip, 100%x, 100%";
Debug.ShouldStop(1073741824);
main.mostCurrent._pnldetail.runVoidMethod ("AddView",(Object)((main.mostCurrent._svdetail.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(RemoteObject.solve(new RemoteObject[] {main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 60)))}, "-",1, 1)));
 BA.debugLineNum = 192;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _createitem(RemoteObject _entry,RemoteObject _width,RemoteObject _height) throws Exception{
try {
		Debug.PushSubsStack("CreateItem (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,273);
if (RapidSub.canDelegate("createitem")) { return gr.mythologia.main.remoteMe.runUserSub(false, "main","createitem", _entry, _width, _height);}
RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _bp = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
RemoteObject _pbg = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _bbg = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
RemoteObject _pnlimg = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
RemoteObject _id = RemoteObject.createImmutable("");
RemoteObject _imgs = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _imgname = RemoteObject.createImmutable("");
RemoteObject _bmp = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper");
RemoteObject _iv = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
RemoteObject _biv = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
RemoteObject _lblname = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _bname = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
RemoteObject _lbldesc = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _bdesc = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
RemoteObject _dtext = RemoteObject.createImmutable("");
Debug.locals.put("Entry", _entry);
Debug.locals.put("Width", _width);
Debug.locals.put("Height", _height);
 BA.debugLineNum = 273;BA.debugLine="Sub CreateItem(Entry As Map, Width As Int, Height";
Debug.ShouldStop(65536);
 BA.debugLineNum = 274;BA.debugLine="Dim p As Panel : p.Initialize(\"pnlItem\")";
Debug.ShouldStop(131072);
_p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("p", _p);
 BA.debugLineNum = 274;BA.debugLine="Dim p As Panel : p.Initialize(\"pnlItem\")";
Debug.ShouldStop(131072);
_p.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("pnlItem")));
 BA.debugLineNum = 275;BA.debugLine="Dim bP As B4XView = p";
Debug.ShouldStop(262144);
_bp = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_bp = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _p.getObject());Debug.locals.put("bP", _bp);Debug.locals.put("bP", _bp);
 BA.debugLineNum = 276;BA.debugLine="bP.Tag = Entry";
Debug.ShouldStop(524288);
_bp.runMethod(false,"setTag",(_entry.getObject()));
 BA.debugLineNum = 277;BA.debugLine="bP.SetLayoutAnimated(0, 0, 0, Width, Height)";
Debug.ShouldStop(1048576);
_bp.runVoidMethod ("SetLayoutAnimated",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(_width),(Object)(_height));
 BA.debugLineNum = 280;BA.debugLine="Dim pBg As Panel : pBg.Initialize(\"pnlItem\")";
Debug.ShouldStop(8388608);
_pbg = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("pBg", _pbg);
 BA.debugLineNum = 280;BA.debugLine="Dim pBg As Panel : pBg.Initialize(\"pnlItem\")";
Debug.ShouldStop(8388608);
_pbg.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("pnlItem")));
 BA.debugLineNum = 281;BA.debugLine="Dim bBg As B4XView = pBg";
Debug.ShouldStop(16777216);
_bbg = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_bbg = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _pbg.getObject());Debug.locals.put("bBg", _bbg);Debug.locals.put("bBg", _bbg);
 BA.debugLineNum = 282;BA.debugLine="bBg.Tag = Entry";
Debug.ShouldStop(33554432);
_bbg.runMethod(false,"setTag",(_entry.getObject()));
 BA.debugLineNum = 283;BA.debugLine="bP.AddView(bBg, 2dip, 2dip, Width - 4dip, Height";
Debug.ShouldStop(67108864);
_bp.runVoidMethod ("AddView",(Object)((_bbg.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 2)))),(Object)(RemoteObject.solve(new RemoteObject[] {_width,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 4)))}, "-",1, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {_height,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 4)))}, "-",1, 1)));
 BA.debugLineNum = 284;BA.debugLine="bBg.Color = 0xFF1A1A35";
Debug.ShouldStop(134217728);
_bbg.runMethod(true,"setColor",BA.numberCast(int.class, ((int)0xff1a1a35)));
 BA.debugLineNum = 288;BA.debugLine="Dim pnlImg As B4XView = xui.CreatePanel(\"\")";
Debug.ShouldStop(-2147483648);
_pnlimg = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_pnlimg = main._xui.runMethod(false,"CreatePanel",main.processBA,(Object)(RemoteObject.createImmutable("")));Debug.locals.put("pnlImg", _pnlimg);Debug.locals.put("pnlImg", _pnlimg);
 BA.debugLineNum = 289;BA.debugLine="pBg.AddView(pnlImg, 8dip, 8dip, 74dip, 74dip)";
Debug.ShouldStop(1);
_pbg.runVoidMethod ("AddView",(Object)((_pnlimg.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 8)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 8)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 74)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 74)))));
 BA.debugLineNum = 290;BA.debugLine="pnlImg.Color = 0xFF242445";
Debug.ShouldStop(2);
_pnlimg.runMethod(true,"setColor",BA.numberCast(int.class, ((int)0xff242445)));
 BA.debugLineNum = 292;BA.debugLine="Dim id As String = Entry.Get(\"id\")";
Debug.ShouldStop(8);
_id = BA.ObjectToString(_entry.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("id")))));Debug.locals.put("id", _id);Debug.locals.put("id", _id);
 BA.debugLineNum = 293;BA.debugLine="If ImageMap.ContainsKey(id) Then";
Debug.ShouldStop(16);
if (main._imagemap.runMethod(true,"ContainsKey",(Object)((_id))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 294;BA.debugLine="Dim imgs As List = ImageMap.Get(id)";
Debug.ShouldStop(32);
_imgs = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_imgs = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), main._imagemap.runMethod(false,"Get",(Object)((_id))));Debug.locals.put("imgs", _imgs);Debug.locals.put("imgs", _imgs);
 BA.debugLineNum = 295;BA.debugLine="If imgs.Size > 0 Then";
Debug.ShouldStop(64);
if (RemoteObject.solveBoolean(">",_imgs.runMethod(true,"getSize"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 296;BA.debugLine="Try";
Debug.ShouldStop(128);
try { BA.debugLineNum = 297;BA.debugLine="Dim imgName As String = imgs.Get(0)";
Debug.ShouldStop(256);
_imgname = BA.ObjectToString(_imgs.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 0))));Debug.locals.put("imgName", _imgname);Debug.locals.put("imgName", _imgname);
 BA.debugLineNum = 298;BA.debugLine="Dim bmp As B4XBitmap = xui.LoadBitmapResize(Fi";
Debug.ShouldStop(512);
_bmp = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper");
_bmp = main._xui.runMethod(false,"LoadBitmapResize",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.concat(RemoteObject.createImmutable("images/images/"),_imgname)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 74)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 74)))),(Object)(main.mostCurrent.__c.getField(true,"True")));Debug.locals.put("bmp", _bmp);Debug.locals.put("bmp", _bmp);
 BA.debugLineNum = 299;BA.debugLine="Dim iv As ImageView : iv.Initialize(\"\")";
Debug.ShouldStop(1024);
_iv = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");Debug.locals.put("iv", _iv);
 BA.debugLineNum = 299;BA.debugLine="Dim iv As ImageView : iv.Initialize(\"\")";
Debug.ShouldStop(1024);
_iv.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 300;BA.debugLine="Dim biv As B4XView = iv";
Debug.ShouldStop(2048);
_biv = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_biv = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _iv.getObject());Debug.locals.put("biv", _biv);Debug.locals.put("biv", _biv);
 BA.debugLineNum = 301;BA.debugLine="biv.SetBitmap(bmp)";
Debug.ShouldStop(4096);
_biv.runVoidMethod ("SetBitmap",(Object)((_bmp.getObject())));
 BA.debugLineNum = 302;BA.debugLine="pnlImg.AddView(biv, 0, 0, 74dip, 74dip)";
Debug.ShouldStop(8192);
_pnlimg.runVoidMethod ("AddView",(Object)((_biv.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 74)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 74)))));
 Debug.CheckDeviceExceptions();
} 
       catch (Exception e28) {
			BA.rdebugUtils.runVoidMethod("setLastException",main.processBA, e28.toString()); BA.debugLineNum = 304;BA.debugLine="Log(\"Image Load Fail: \" & imgs.Get(0))";
Debug.ShouldStop(32768);
main.mostCurrent.__c.runVoidMethod ("LogImpl","2720927",RemoteObject.concat(RemoteObject.createImmutable("Image Load Fail: "),_imgs.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 0)))),0);
 };
 };
 };
 BA.debugLineNum = 310;BA.debugLine="Dim lblName As Label : lblName.Initialize(\"\")";
Debug.ShouldStop(2097152);
_lblname = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblName", _lblname);
 BA.debugLineNum = 310;BA.debugLine="Dim lblName As Label : lblName.Initialize(\"\")";
Debug.ShouldStop(2097152);
_lblname.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 311;BA.debugLine="Dim bName As B4XView = lblName";
Debug.ShouldStop(4194304);
_bname = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_bname = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _lblname.getObject());Debug.locals.put("bName", _bname);Debug.locals.put("bName", _bname);
 BA.debugLineNum = 312;BA.debugLine="bName.TextColor = 0xFFEDD060";
Debug.ShouldStop(8388608);
_bname.runMethod(true,"setTextColor",BA.numberCast(int.class, ((int)0xffedd060)));
 BA.debugLineNum = 313;BA.debugLine="bName.TextSize = 18";
Debug.ShouldStop(16777216);
_bname.runMethod(true,"setTextSize",BA.numberCast(float.class, 18));
 BA.debugLineNum = 314;BA.debugLine="bName.Font = xui.CreateDefaultBoldFont(18)";
Debug.ShouldStop(33554432);
_bname.runMethod(false,"setFont",main._xui.runMethod(false,"CreateDefaultBoldFont",(Object)(BA.numberCast(float.class, 18))));
 BA.debugLineNum = 315;BA.debugLine="bName.Text = Entry.Get(\"name\")";
Debug.ShouldStop(67108864);
_bname.runMethod(true,"setText",BA.ObjectToCharSequence(_entry.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("name"))))));
 BA.debugLineNum = 316;BA.debugLine="pBg.AddView(bName, 90dip, 10dip, Width - 100dip,";
Debug.ShouldStop(134217728);
_pbg.runVoidMethod ("AddView",(Object)((_bname.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 90)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(RemoteObject.solve(new RemoteObject[] {_width,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))}, "-",1, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))));
 BA.debugLineNum = 319;BA.debugLine="Dim lblDesc As Label : lblDesc.Initialize(\"\")";
Debug.ShouldStop(1073741824);
_lbldesc = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblDesc", _lbldesc);
 BA.debugLineNum = 319;BA.debugLine="Dim lblDesc As Label : lblDesc.Initialize(\"\")";
Debug.ShouldStop(1073741824);
_lbldesc.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 320;BA.debugLine="Dim bDesc As B4XView = lblDesc";
Debug.ShouldStop(-2147483648);
_bdesc = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_bdesc = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _lbldesc.getObject());Debug.locals.put("bDesc", _bdesc);Debug.locals.put("bDesc", _bdesc);
 BA.debugLineNum = 321;BA.debugLine="bDesc.TextColor = 0xFFA89878";
Debug.ShouldStop(1);
_bdesc.runMethod(true,"setTextColor",BA.numberCast(int.class, ((int)0xffa89878)));
 BA.debugLineNum = 322;BA.debugLine="bDesc.TextSize = 13";
Debug.ShouldStop(2);
_bdesc.runMethod(true,"setTextSize",BA.numberCast(float.class, 13));
 BA.debugLineNum = 323;BA.debugLine="Dim dText As String = Entry.Get(\"description\")";
Debug.ShouldStop(4);
_dtext = BA.ObjectToString(_entry.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("description")))));Debug.locals.put("dText", _dtext);Debug.locals.put("dText", _dtext);
 BA.debugLineNum = 324;BA.debugLine="If dText.Length > 100 Then dText = dText.SubStrin";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean(">",_dtext.runMethod(true,"length"),BA.numberCast(double.class, 100))) { 
_dtext = RemoteObject.concat(_dtext.runMethod(true,"substring",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 100))).runMethod(true,"replace",(Object)(main.mostCurrent.__c.getField(true,"CRLF")),(Object)(RemoteObject.createImmutable(" "))),RemoteObject.createImmutable("..."));Debug.locals.put("dText", _dtext);};
 BA.debugLineNum = 325;BA.debugLine="bDesc.Text = dText";
Debug.ShouldStop(16);
_bdesc.runMethod(true,"setText",BA.ObjectToCharSequence(_dtext));
 BA.debugLineNum = 326;BA.debugLine="pBg.AddView(bDesc, 90dip, 40dip, Width - 100dip,";
Debug.ShouldStop(32);
_pbg.runVoidMethod ("AddView",(Object)((_bdesc.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 90)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))),(Object)(RemoteObject.solve(new RemoteObject[] {_width,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))}, "-",1, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 328;BA.debugLine="Return p";
Debug.ShouldStop(128);
if (true) return RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _p.getObject());
 BA.debugLineNum = 329;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable(null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _createsplashscreen() throws Exception{
try {
		Debug.PushSubsStack("CreateSplashScreen (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,111);
if (RapidSub.canDelegate("createsplashscreen")) { return gr.mythologia.main.remoteMe.runUserSub(false, "main","createsplashscreen");}
RemoteObject _lblicon = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _bicon = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
RemoteObject _lbltitle = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _btitle = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
RemoteObject _lblload = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _bload = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
 BA.debugLineNum = 111;BA.debugLine="Sub CreateSplashScreen";
Debug.ShouldStop(16384);
 BA.debugLineNum = 112;BA.debugLine="pnlSplash = xui.CreatePanel(\"\")";
Debug.ShouldStop(32768);
main.mostCurrent._pnlsplash = main._xui.runMethod(false,"CreatePanel",main.processBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 113;BA.debugLine="pnlSplash.Color = 0xFF111128";
Debug.ShouldStop(65536);
main.mostCurrent._pnlsplash.runMethod(true,"setColor",BA.numberCast(int.class, ((int)0xff111128)));
 BA.debugLineNum = 114;BA.debugLine="Activity.AddView(pnlSplash, 0, 0, 100%x, 100%y)";
Debug.ShouldStop(131072);
main.mostCurrent._activity.runVoidMethod ("AddView",(Object)((main.mostCurrent._pnlsplash.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 117;BA.debugLine="Dim lblIcon As Label : lblIcon.Initialize(\"\")";
Debug.ShouldStop(1048576);
_lblicon = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblIcon", _lblicon);
 BA.debugLineNum = 117;BA.debugLine="Dim lblIcon As Label : lblIcon.Initialize(\"\")";
Debug.ShouldStop(1048576);
_lblicon.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 118;BA.debugLine="Dim bIcon As B4XView = lblIcon";
Debug.ShouldStop(2097152);
_bicon = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_bicon = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _lblicon.getObject());Debug.locals.put("bIcon", _bicon);Debug.locals.put("bIcon", _bicon);
 BA.debugLineNum = 119;BA.debugLine="bIcon.Text = \"🏛️\"";
Debug.ShouldStop(4194304);
_bicon.runMethod(true,"setText",BA.ObjectToCharSequence("🏛️"));
 BA.debugLineNum = 120;BA.debugLine="bIcon.TextSize = 80";
Debug.ShouldStop(8388608);
_bicon.runMethod(true,"setTextSize",BA.numberCast(float.class, 80));
 BA.debugLineNum = 121;BA.debugLine="bIcon.SetTextAlignment(\"CENTER\", \"CENTER\")";
Debug.ShouldStop(16777216);
_bicon.runVoidMethod ("SetTextAlignment",(Object)(BA.ObjectToString("CENTER")),(Object)(RemoteObject.createImmutable("CENTER")));
 BA.debugLineNum = 122;BA.debugLine="pnlSplash.AddView(bIcon, 0, 30%y, 100%x, 100dip)";
Debug.ShouldStop(33554432);
main.mostCurrent._pnlsplash.runVoidMethod ("AddView",(Object)((_bicon.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 30)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))));
 BA.debugLineNum = 125;BA.debugLine="Dim lblTitle As Label : lblTitle.Initialize(\"\")";
Debug.ShouldStop(268435456);
_lbltitle = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblTitle", _lbltitle);
 BA.debugLineNum = 125;BA.debugLine="Dim lblTitle As Label : lblTitle.Initialize(\"\")";
Debug.ShouldStop(268435456);
_lbltitle.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 126;BA.debugLine="Dim bTitle As B4XView = lblTitle";
Debug.ShouldStop(536870912);
_btitle = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_btitle = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _lbltitle.getObject());Debug.locals.put("bTitle", _btitle);Debug.locals.put("bTitle", _btitle);
 BA.debugLineNum = 127;BA.debugLine="bTitle.Text = \"NEW MYTHOLOGY\"";
Debug.ShouldStop(1073741824);
_btitle.runMethod(true,"setText",BA.ObjectToCharSequence("NEW MYTHOLOGY"));
 BA.debugLineNum = 128;BA.debugLine="bTitle.TextColor = 0xFFEDD060";
Debug.ShouldStop(-2147483648);
_btitle.runMethod(true,"setTextColor",BA.numberCast(int.class, ((int)0xffedd060)));
 BA.debugLineNum = 129;BA.debugLine="bTitle.TextSize = 28";
Debug.ShouldStop(1);
_btitle.runMethod(true,"setTextSize",BA.numberCast(float.class, 28));
 BA.debugLineNum = 130;BA.debugLine="bTitle.Font = xui.CreateDefaultBoldFont(28)";
Debug.ShouldStop(2);
_btitle.runMethod(false,"setFont",main._xui.runMethod(false,"CreateDefaultBoldFont",(Object)(BA.numberCast(float.class, 28))));
 BA.debugLineNum = 131;BA.debugLine="bTitle.SetTextAlignment(\"CENTER\", \"CENTER\")";
Debug.ShouldStop(4);
_btitle.runVoidMethod ("SetTextAlignment",(Object)(BA.ObjectToString("CENTER")),(Object)(RemoteObject.createImmutable("CENTER")));
 BA.debugLineNum = 132;BA.debugLine="pnlSplash.AddView(bTitle, 0, 45%y, 100%x, 40dip)";
Debug.ShouldStop(8);
main.mostCurrent._pnlsplash.runVoidMethod ("AddView",(Object)((_btitle.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 45)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 40)))));
 BA.debugLineNum = 135;BA.debugLine="Dim lblLoad As Label : lblLoad.Initialize(\"\")";
Debug.ShouldStop(64);
_lblload = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblLoad", _lblload);
 BA.debugLineNum = 135;BA.debugLine="Dim lblLoad As Label : lblLoad.Initialize(\"\")";
Debug.ShouldStop(64);
_lblload.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 136;BA.debugLine="Dim bLoad As B4XView = lblLoad";
Debug.ShouldStop(128);
_bload = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_bload = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _lblload.getObject());Debug.locals.put("bLoad", _bload);Debug.locals.put("bLoad", _bload);
 BA.debugLineNum = 137;BA.debugLine="bLoad.Text = \"Loading myths...\"";
Debug.ShouldStop(256);
_bload.runMethod(true,"setText",BA.ObjectToCharSequence("Loading myths..."));
 BA.debugLineNum = 138;BA.debugLine="bLoad.TextColor = 0xFFA89878";
Debug.ShouldStop(512);
_bload.runMethod(true,"setTextColor",BA.numberCast(int.class, ((int)0xffa89878)));
 BA.debugLineNum = 139;BA.debugLine="bLoad.TextSize = 14";
Debug.ShouldStop(1024);
_bload.runMethod(true,"setTextSize",BA.numberCast(float.class, 14));
 BA.debugLineNum = 140;BA.debugLine="bLoad.SetTextAlignment(\"CENTER\", \"CENTER\")";
Debug.ShouldStop(2048);
_bload.runVoidMethod ("SetTextAlignment",(Object)(BA.ObjectToString("CENTER")),(Object)(RemoteObject.createImmutable("CENTER")));
 BA.debugLineNum = 141;BA.debugLine="pnlSplash.AddView(bLoad, 0, 80%y, 100%x, 30dip)";
Debug.ShouldStop(4096);
main.mostCurrent._pnlsplash.runVoidMethod ("AddView",(Object)((_bload.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 80)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))));
 BA.debugLineNum = 142;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _filllist(RemoteObject _query) throws Exception{
try {
		Debug.PushSubsStack("FillList (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,248);
if (RapidSub.canDelegate("filllist")) { return gr.mythologia.main.remoteMe.runUserSub(false, "main","filllist", _query);}
RemoteObject _cury = RemoteObject.createImmutable(0);
RemoteObject _itemheight = RemoteObject.createImmutable(0);
RemoteObject _entry = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _name = RemoteObject.createImmutable("");
RemoteObject _desc = RemoteObject.createImmutable("");
RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
Debug.locals.put("Query", _query);
 BA.debugLineNum = 248;BA.debugLine="Sub FillList(Query As String)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 249;BA.debugLine="If MainUIReady = False Then Return";
Debug.ShouldStop(16777216);
if (RemoteObject.solveBoolean("=",main._mainuiready,main.mostCurrent.__c.getField(true,"False"))) { 
if (true) return RemoteObject.createImmutable("");};
 BA.debugLineNum = 250;BA.debugLine="svEntries.Panel.RemoveAllViews";
Debug.ShouldStop(33554432);
main.mostCurrent._sventries.runMethod(false,"getPanel").runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 251;BA.debugLine="Dim curY As Int = 0";
Debug.ShouldStop(67108864);
_cury = BA.numberCast(int.class, 0);Debug.locals.put("curY", _cury);Debug.locals.put("curY", _cury);
 BA.debugLineNum = 252;BA.debugLine="Dim itemHeight As Int = 90dip";
Debug.ShouldStop(134217728);
_itemheight = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 90)));Debug.locals.put("itemHeight", _itemheight);Debug.locals.put("itemHeight", _itemheight);
 BA.debugLineNum = 253;BA.debugLine="Query = Query.ToLowerCase";
Debug.ShouldStop(268435456);
_query = _query.runMethod(true,"toLowerCase");Debug.locals.put("Query", _query);
 BA.debugLineNum = 255;BA.debugLine="For Each entry As Map In MythologyData";
Debug.ShouldStop(1073741824);
_entry = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
{
final RemoteObject group6 = main._mythologydata;
final int groupLen6 = group6.runMethod(true,"getSize").<Integer>get()
;int index6 = 0;
;
for (; index6 < groupLen6;index6++){
_entry = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), group6.runMethod(false,"Get",index6));Debug.locals.put("entry", _entry);
Debug.locals.put("entry", _entry);
 BA.debugLineNum = 256;BA.debugLine="Dim name As String = entry.Get(\"name\")";
Debug.ShouldStop(-2147483648);
_name = BA.ObjectToString(_entry.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("name")))));Debug.locals.put("name", _name);Debug.locals.put("name", _name);
 BA.debugLineNum = 257;BA.debugLine="Dim desc As String = entry.Get(\"description\")";
Debug.ShouldStop(1);
_desc = BA.ObjectToString(_entry.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("description")))));Debug.locals.put("desc", _desc);Debug.locals.put("desc", _desc);
 BA.debugLineNum = 259;BA.debugLine="If Query = \"\" Or name.ToLowerCase.Contains(Query";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean("=",_query,BA.ObjectToString("")) || RemoteObject.solveBoolean(".",_name.runMethod(true,"toLowerCase").runMethod(true,"contains",(Object)(_query))) || RemoteObject.solveBoolean(".",_desc.runMethod(true,"toLowerCase").runMethod(true,"contains",(Object)(_query)))) { 
 BA.debugLineNum = 260;BA.debugLine="Dim p As B4XView = CreateItem(entry, svEntries.";
Debug.ShouldStop(8);
_p = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_p = _createitem(_entry,main.mostCurrent._sventries.runMethod(true,"getWidth"),_itemheight);Debug.locals.put("p", _p);Debug.locals.put("p", _p);
 BA.debugLineNum = 261;BA.debugLine="svEntries.Panel.AddView(p, 0, curY, svEntries.W";
Debug.ShouldStop(16);
main.mostCurrent._sventries.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((_p.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(_cury),(Object)(main.mostCurrent._sventries.runMethod(true,"getWidth")),(Object)(_itemheight));
 BA.debugLineNum = 262;BA.debugLine="curY = curY + itemHeight";
Debug.ShouldStop(32);
_cury = RemoteObject.solve(new RemoteObject[] {_cury,_itemheight}, "+",1, 1);Debug.locals.put("curY", _cury);
 };
 }
}Debug.locals.put("entry", _entry);
;
 BA.debugLineNum = 265;BA.debugLine="svEntries.Panel.Height = curY";
Debug.ShouldStop(256);
main.mostCurrent._sventries.runMethod(false,"getPanel").runMethod(true,"setHeight",_cury);
 BA.debugLineNum = 266;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 25;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 28;BA.debugLine="Private svEntries As ScrollView";
main.mostCurrent._sventries = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");
 //BA.debugLineNum = 29;BA.debugLine="Private pnlMain As B4XView";
main.mostCurrent._pnlmain = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
 //BA.debugLineNum = 30;BA.debugLine="Private txtSearch As B4XView";
main.mostCurrent._txtsearch = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
 //BA.debugLineNum = 33;BA.debugLine="Private pnlDetail As B4XView";
main.mostCurrent._pnldetail = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
 //BA.debugLineNum = 34;BA.debugLine="Private lblDetailName As B4XView";
main.mostCurrent._lbldetailname = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
 //BA.debugLineNum = 35;BA.debugLine="Private lblDetailDesc As B4XView ' This will be a";
main.mostCurrent._lbldetaildesc = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
 //BA.debugLineNum = 36;BA.debugLine="Private svDetail As ScrollView";
main.mostCurrent._svdetail = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");
 //BA.debugLineNum = 37;BA.debugLine="Private svDetail As ScrollView";
main.mostCurrent._svdetail = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");
 //BA.debugLineNum = 38;BA.debugLine="Private btnBack As B4XView";
main.mostCurrent._btnback = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
 //BA.debugLineNum = 39;BA.debugLine="Private lblLang As B4XView ' Global reference for";
main.mostCurrent._lbllang = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
 //BA.debugLineNum = 40;BA.debugLine="Private pnlSplash As B4XView ' Premium splash scr";
main.mostCurrent._pnlsplash = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _lbllang_click() throws Exception{
try {
		Debug.PushSubsStack("lblLang_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,144);
if (RapidSub.canDelegate("lbllang_click")) { return gr.mythologia.main.remoteMe.runUserSub(false, "main","lbllang_click");}
 BA.debugLineNum = 144;BA.debugLine="Public Sub lblLang_Click";
Debug.ShouldStop(32768);
 BA.debugLineNum = 145;BA.debugLine="Log(\"Language Clicked\")";
Debug.ShouldStop(65536);
main.mostCurrent.__c.runVoidMethod ("LogImpl","2262145",RemoteObject.createImmutable("Language Clicked"),0);
 BA.debugLineNum = 146;BA.debugLine="If CurrentLanguage = \"el\" Then";
Debug.ShouldStop(131072);
if (RemoteObject.solveBoolean("=",main._currentlanguage,BA.ObjectToString("el"))) { 
 BA.debugLineNum = 147;BA.debugLine="CurrentLanguage = \"en\"";
Debug.ShouldStop(262144);
main._currentlanguage = BA.ObjectToString("en");
 BA.debugLineNum = 148;BA.debugLine="lblLang.Text = \"EN\"";
Debug.ShouldStop(524288);
main.mostCurrent._lbllang.runMethod(true,"setText",BA.ObjectToCharSequence("EN"));
 }else {
 BA.debugLineNum = 150;BA.debugLine="CurrentLanguage = \"el\"";
Debug.ShouldStop(2097152);
main._currentlanguage = BA.ObjectToString("el");
 BA.debugLineNum = 151;BA.debugLine="lblLang.Text = \"EL\"";
Debug.ShouldStop(4194304);
main.mostCurrent._lbllang.runMethod(true,"setText",BA.ObjectToCharSequence("EL"));
 };
 BA.debugLineNum = 154;BA.debugLine="FillList(txtSearch.Text)";
Debug.ShouldStop(33554432);
_filllist(main.mostCurrent._txtsearch.runMethod(true,"getText"));
 BA.debugLineNum = 155;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _loaddata() throws Exception{
try {
		Debug.PushSubsStack("LoadData (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,198);
if (RapidSub.canDelegate("loaddata")) { return gr.mythologia.main.remoteMe.runUserSub(false, "main","loaddata");}
RemoteObject _smap = RemoteObject.createImmutable("");
RemoteObject _jp = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser");
RemoteObject _sdata = RemoteObject.createImmutable("");
 BA.debugLineNum = 198;BA.debugLine="Sub LoadData";
Debug.ShouldStop(32);
 BA.debugLineNum = 199;BA.debugLine="Try";
Debug.ShouldStop(64);
try { BA.debugLineNum = 201;BA.debugLine="Dim sMap As String = File.ReadString(File.DirAss";
Debug.ShouldStop(256);
_smap = main.mostCurrent.__c.getField(false,"File").runMethod(true,"ReadString",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("image_map.js")));Debug.locals.put("sMap", _smap);Debug.locals.put("sMap", _smap);
 BA.debugLineNum = 202;BA.debugLine="sMap = CleanJS(sMap, \"ENTRY_IMAGES\")";
Debug.ShouldStop(512);
_smap = _cleanjs(_smap,RemoteObject.createImmutable("ENTRY_IMAGES"));Debug.locals.put("sMap", _smap);
 BA.debugLineNum = 203;BA.debugLine="Dim jp As JSONParser";
Debug.ShouldStop(1024);
_jp = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser");Debug.locals.put("jp", _jp);
 BA.debugLineNum = 204;BA.debugLine="jp.Initialize(sMap)";
Debug.ShouldStop(2048);
_jp.runVoidMethod ("Initialize",(Object)(_smap));
 BA.debugLineNum = 205;BA.debugLine="ImageMap = jp.NextObject";
Debug.ShouldStop(4096);
main._imagemap = _jp.runMethod(false,"NextObject");
 BA.debugLineNum = 206;BA.debugLine="Log(\"Loaded \" & ImageMap.Size & \" image mappings";
Debug.ShouldStop(8192);
main.mostCurrent.__c.runVoidMethod ("LogImpl","2458760",RemoteObject.concat(RemoteObject.createImmutable("Loaded "),main._imagemap.runMethod(true,"getSize"),RemoteObject.createImmutable(" image mappings.")),0);
 BA.debugLineNum = 209;BA.debugLine="Dim sData As String = File.ReadString(File.DirAs";
Debug.ShouldStop(65536);
_sdata = main.mostCurrent.__c.getField(false,"File").runMethod(true,"ReadString",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("mythology_data.js")));Debug.locals.put("sData", _sdata);Debug.locals.put("sData", _sdata);
 BA.debugLineNum = 210;BA.debugLine="sData = CleanJS(sData, \"MYTHOLOGY_DATA\")";
Debug.ShouldStop(131072);
_sdata = _cleanjs(_sdata,RemoteObject.createImmutable("MYTHOLOGY_DATA"));Debug.locals.put("sData", _sdata);
 BA.debugLineNum = 211;BA.debugLine="jp.Initialize(sData)";
Debug.ShouldStop(262144);
_jp.runVoidMethod ("Initialize",(Object)(_sdata));
 BA.debugLineNum = 212;BA.debugLine="MythologyData = jp.NextArray";
Debug.ShouldStop(524288);
main._mythologydata = _jp.runMethod(false,"NextArray");
 BA.debugLineNum = 213;BA.debugLine="Log(\"Loaded \" & MythologyData.Size & \" mythology";
Debug.ShouldStop(1048576);
main.mostCurrent.__c.runVoidMethod ("LogImpl","2458767",RemoteObject.concat(RemoteObject.createImmutable("Loaded "),main._mythologydata.runMethod(true,"getSize"),RemoteObject.createImmutable(" mythology entries.")),0);
 Debug.CheckDeviceExceptions();
} 
       catch (Exception e14) {
			BA.rdebugUtils.runVoidMethod("setLastException",main.processBA, e14.toString()); BA.debugLineNum = 216;BA.debugLine="Log(\"LoadData Error: \" & LastException.Message)";
Debug.ShouldStop(8388608);
main.mostCurrent.__c.runVoidMethod ("LogImpl","2458770",RemoteObject.concat(RemoteObject.createImmutable("LoadData Error: "),main.mostCurrent.__c.runMethod(false,"LastException",main.mostCurrent.activityBA).runMethod(true,"getMessage")),0);
 BA.debugLineNum = 217;BA.debugLine="MsgboxAsync(\"Error loading data: \" & LastExcepti";
Debug.ShouldStop(16777216);
main.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Error loading data: "),main.mostCurrent.__c.runMethod(false,"LastException",main.mostCurrent.activityBA).runMethod(true,"getMessage")))),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),main.processBA);
 };
 BA.debugLineNum = 219;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _pnlitem_click() throws Exception{
try {
		Debug.PushSubsStack("pnlItem_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,268);
if (RapidSub.canDelegate("pnlitem_click")) { return gr.mythologia.main.remoteMe.runUserSub(false, "main","pnlitem_click");}
RemoteObject _pnl = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
 BA.debugLineNum = 268;BA.debugLine="Sub pnlItem_Click";
Debug.ShouldStop(2048);
 BA.debugLineNum = 269;BA.debugLine="Dim pnl As B4XView = Sender";
Debug.ShouldStop(4096);
_pnl = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_pnl = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), main.mostCurrent.__c.runMethod(false,"Sender",main.mostCurrent.activityBA));Debug.locals.put("pnl", _pnl);Debug.locals.put("pnl", _pnl);
 BA.debugLineNum = 270;BA.debugLine="ShowDetail(pnl.Tag)";
Debug.ShouldStop(8192);
_showdetail(RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.Map"), _pnl.runMethod(false,"getTag")));
 BA.debugLineNum = 271;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
starter_subs_0._process_globals();
xuiviewsutils_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("gr.mythologia.main");
starter.myClass = BA.getDeviceClass ("gr.mythologia.starter");
animatedcounter.myClass = BA.getDeviceClass ("gr.mythologia.animatedcounter");
anotherprogressbar.myClass = BA.getDeviceClass ("gr.mythologia.anotherprogressbar");
b4xbreadcrumb.myClass = BA.getDeviceClass ("gr.mythologia.b4xbreadcrumb");
b4xcolortemplate.myClass = BA.getDeviceClass ("gr.mythologia.b4xcolortemplate");
b4xcombobox.myClass = BA.getDeviceClass ("gr.mythologia.b4xcombobox");
b4xdatetemplate.myClass = BA.getDeviceClass ("gr.mythologia.b4xdatetemplate");
b4xdialog.myClass = BA.getDeviceClass ("gr.mythologia.b4xdialog");
b4xfloattextfield.myClass = BA.getDeviceClass ("gr.mythologia.b4xfloattextfield");
b4ximageview.myClass = BA.getDeviceClass ("gr.mythologia.b4ximageview");
b4xinputtemplate.myClass = BA.getDeviceClass ("gr.mythologia.b4xinputtemplate");
b4xlisttemplate.myClass = BA.getDeviceClass ("gr.mythologia.b4xlisttemplate");
b4xloadingindicator.myClass = BA.getDeviceClass ("gr.mythologia.b4xloadingindicator");
b4xlongtexttemplate.myClass = BA.getDeviceClass ("gr.mythologia.b4xlongtexttemplate");
b4xplusminus.myClass = BA.getDeviceClass ("gr.mythologia.b4xplusminus");
b4xprogressdialog.myClass = BA.getDeviceClass ("gr.mythologia.b4xprogressdialog");
b4xradiobutton.myClass = BA.getDeviceClass ("gr.mythologia.b4xradiobutton");
b4xsearchtemplate.myClass = BA.getDeviceClass ("gr.mythologia.b4xsearchtemplate");
b4xseekbar.myClass = BA.getDeviceClass ("gr.mythologia.b4xseekbar");
b4xsignaturetemplate.myClass = BA.getDeviceClass ("gr.mythologia.b4xsignaturetemplate");
b4xswitch.myClass = BA.getDeviceClass ("gr.mythologia.b4xswitch");
b4xtimedtemplate.myClass = BA.getDeviceClass ("gr.mythologia.b4xtimedtemplate");
madewithlove.myClass = BA.getDeviceClass ("gr.mythologia.madewithlove");
b4xformatter.myClass = BA.getDeviceClass ("gr.mythologia.b4xformatter");
roundslider.myClass = BA.getDeviceClass ("gr.mythologia.roundslider");
scrollinglabel.myClass = BA.getDeviceClass ("gr.mythologia.scrollinglabel");
swiftbutton.myClass = BA.getDeviceClass ("gr.mythologia.swiftbutton");
xuiviewsutils.myClass = BA.getDeviceClass ("gr.mythologia.xuiviewsutils");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 18;BA.debugLine="Private xui As XUI";
main._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 19;BA.debugLine="Public MythologyData As List";
main._mythologydata = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 20;BA.debugLine="Public ImageMap As Map";
main._imagemap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
 //BA.debugLineNum = 21;BA.debugLine="Public CurrentLanguage As String = \"el\"";
main._currentlanguage = BA.ObjectToString("el");
 //BA.debugLineNum = 22;BA.debugLine="Private MainUIReady As Boolean = False ' Flag to";
main._mainuiready = main.mostCurrent.__c.getField(true,"False");
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _showdetail(RemoteObject _entry) throws Exception{
try {
		Debug.PushSubsStack("ShowDetail (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,335);
if (RapidSub.canDelegate("showdetail")) { return gr.mythologia.main.remoteMe.runUserSub(false, "main","showdetail", _entry);}
RemoteObject _cury = RemoteObject.createImmutable(0);
RemoteObject _id = RemoteObject.createImmutable("");
RemoteObject _imgs = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _hsv = RemoteObject.declareNull("anywheresoftware.b4a.objects.HorizontalScrollViewWrapper");
RemoteObject _curx = RemoteObject.createImmutable(0);
RemoteObject _imgname = RemoteObject.createImmutable("");
RemoteObject _pnlimg = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
RemoteObject _bmp = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper");
RemoteObject _iv = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
RemoteObject _biv = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
RemoteObject _lblheader = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _bheader = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
RemoteObject _lbldesc = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _bdesc = RemoteObject.declareNull("anywheresoftware.b4a.objects.B4XViewWrapper");
RemoteObject _fulltext = RemoteObject.createImmutable("");
RemoteObject _charcount = RemoteObject.createImmutable(0);
RemoteObject _estimatedlines = RemoteObject.createImmutable(0);
RemoteObject _textheight = RemoteObject.createImmutable(0);
Debug.locals.put("Entry", _entry);
 BA.debugLineNum = 335;BA.debugLine="Sub ShowDetail(Entry As Map)";
Debug.ShouldStop(16384);
 BA.debugLineNum = 336;BA.debugLine="pnlDetail.Visible = True";
Debug.ShouldStop(32768);
main.mostCurrent._pnldetail.runMethod(true,"setVisible",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 337;BA.debugLine="pnlDetail.BringToFront";
Debug.ShouldStop(65536);
main.mostCurrent._pnldetail.runVoidMethod ("BringToFront");
 BA.debugLineNum = 338;BA.debugLine="pnlDetail.SetLayoutAnimated(300, 0, 0, 100%x, 100";
Debug.ShouldStop(131072);
main.mostCurrent._pnldetail.runVoidMethod ("SetLayoutAnimated",(Object)(BA.numberCast(int.class, 300)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)));
 BA.debugLineNum = 340;BA.debugLine="lblDetailName.Text = Entry.Get(\"name\")";
Debug.ShouldStop(524288);
main.mostCurrent._lbldetailname.runMethod(true,"setText",BA.ObjectToCharSequence(_entry.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("name"))))));
 BA.debugLineNum = 343;BA.debugLine="svDetail.Panel.RemoveAllViews";
Debug.ShouldStop(4194304);
main.mostCurrent._svdetail.runMethod(false,"getPanel").runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 345;BA.debugLine="Dim curY As Int = 15dip";
Debug.ShouldStop(16777216);
_cury = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 15)));Debug.locals.put("curY", _cury);Debug.locals.put("curY", _cury);
 BA.debugLineNum = 348;BA.debugLine="Dim id As String = Entry.Get(\"id\")";
Debug.ShouldStop(134217728);
_id = BA.ObjectToString(_entry.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("id")))));Debug.locals.put("id", _id);Debug.locals.put("id", _id);
 BA.debugLineNum = 349;BA.debugLine="If ImageMap.ContainsKey(id) Then";
Debug.ShouldStop(268435456);
if (main._imagemap.runMethod(true,"ContainsKey",(Object)((_id))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 350;BA.debugLine="Dim imgs As List = ImageMap.Get(id)";
Debug.ShouldStop(536870912);
_imgs = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
_imgs = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.collections.List"), main._imagemap.runMethod(false,"Get",(Object)((_id))));Debug.locals.put("imgs", _imgs);Debug.locals.put("imgs", _imgs);
 BA.debugLineNum = 351;BA.debugLine="If imgs.Size > 0 Then";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean(">",_imgs.runMethod(true,"getSize"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 353;BA.debugLine="Dim hsv As HorizontalScrollView : hsv.Initializ";
Debug.ShouldStop(1);
_hsv = RemoteObject.createNew ("anywheresoftware.b4a.objects.HorizontalScrollViewWrapper");Debug.locals.put("hsv", _hsv);
 BA.debugLineNum = 353;BA.debugLine="Dim hsv As HorizontalScrollView : hsv.Initializ";
Debug.ShouldStop(1);
_hsv.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(BA.numberCast(int.class, 0)),(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 354;BA.debugLine="svDetail.Panel.AddView(hsv, 0, curY, 100%x, 240";
Debug.ShouldStop(2);
main.mostCurrent._svdetail.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((_hsv.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(_cury),(Object)(main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 240)))));
 BA.debugLineNum = 356;BA.debugLine="Dim curX As Int = 15dip";
Debug.ShouldStop(8);
_curx = main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 15)));Debug.locals.put("curX", _curx);Debug.locals.put("curX", _curx);
 BA.debugLineNum = 357;BA.debugLine="for each imgName as String in imgs";
Debug.ShouldStop(16);
{
final RemoteObject group15 = _imgs;
final int groupLen15 = group15.runMethod(true,"getSize").<Integer>get()
;int index15 = 0;
;
for (; index15 < groupLen15;index15++){
_imgname = BA.ObjectToString(group15.runMethod(false,"Get",index15));Debug.locals.put("imgName", _imgname);
Debug.locals.put("imgName", _imgname);
 BA.debugLineNum = 358;BA.debugLine="Dim pnlImg As B4XView = xui.CreatePanel(\"\")";
Debug.ShouldStop(32);
_pnlimg = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_pnlimg = main._xui.runMethod(false,"CreatePanel",main.processBA,(Object)(RemoteObject.createImmutable("")));Debug.locals.put("pnlImg", _pnlimg);Debug.locals.put("pnlImg", _pnlimg);
 BA.debugLineNum = 359;BA.debugLine="hsv.Panel.AddView(pnlImg, curX, 10dip, 300dip,";
Debug.ShouldStop(64);
_hsv.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((_pnlimg.getObject())),(Object)(_curx),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 300)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 200)))));
 BA.debugLineNum = 360;BA.debugLine="pnlImg.Color = 0xFF242445";
Debug.ShouldStop(128);
_pnlimg.runMethod(true,"setColor",BA.numberCast(int.class, ((int)0xff242445)));
 BA.debugLineNum = 362;BA.debugLine="Try";
Debug.ShouldStop(512);
try { BA.debugLineNum = 363;BA.debugLine="Dim bmp As B4XBitmap = xui.LoadBitmapResize(F";
Debug.ShouldStop(1024);
_bmp = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper");
_bmp = main._xui.runMethod(false,"LoadBitmapResize",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.concat(RemoteObject.createImmutable("images/images/"),_imgname)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 300)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 200)))),(Object)(main.mostCurrent.__c.getField(true,"True")));Debug.locals.put("bmp", _bmp);Debug.locals.put("bmp", _bmp);
 BA.debugLineNum = 364;BA.debugLine="Dim iv As ImageView : iv.Initialize(\"\")";
Debug.ShouldStop(2048);
_iv = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");Debug.locals.put("iv", _iv);
 BA.debugLineNum = 364;BA.debugLine="Dim iv As ImageView : iv.Initialize(\"\")";
Debug.ShouldStop(2048);
_iv.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 365;BA.debugLine="Dim biv As B4XView = iv";
Debug.ShouldStop(4096);
_biv = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_biv = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _iv.getObject());Debug.locals.put("biv", _biv);Debug.locals.put("biv", _biv);
 BA.debugLineNum = 366;BA.debugLine="biv.SetBitmap(bmp)";
Debug.ShouldStop(8192);
_biv.runVoidMethod ("SetBitmap",(Object)((_bmp.getObject())));
 BA.debugLineNum = 367;BA.debugLine="pnlImg.AddView(biv, 0, 0, 300dip, 200dip)";
Debug.ShouldStop(16384);
_pnlimg.runVoidMethod ("AddView",(Object)((_biv.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 300)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 200)))));
 BA.debugLineNum = 368;BA.debugLine="curX = curX + 315dip";
Debug.ShouldStop(32768);
_curx = RemoteObject.solve(new RemoteObject[] {_curx,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 315)))}, "+",1, 1);Debug.locals.put("curX", _curx);
 Debug.CheckDeviceExceptions();
} 
       catch (Exception e28) {
			BA.rdebugUtils.runVoidMethod("setLastException",main.processBA, e28.toString()); BA.debugLineNum = 370;BA.debugLine="Log(\"Detail Image Load Fail: \" & imgName)";
Debug.ShouldStop(131072);
main.mostCurrent.__c.runVoidMethod ("LogImpl","2852003",RemoteObject.concat(RemoteObject.createImmutable("Detail Image Load Fail: "),_imgname),0);
 };
 }
}Debug.locals.put("imgName", _imgname);
;
 BA.debugLineNum = 373;BA.debugLine="hsv.Panel.Width = curX + 15dip";
Debug.ShouldStop(1048576);
_hsv.runMethod(false,"getPanel").runMethod(true,"setWidth",RemoteObject.solve(new RemoteObject[] {_curx,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 15)))}, "+",1, 1));
 BA.debugLineNum = 374;BA.debugLine="curY = curY + 240dip";
Debug.ShouldStop(2097152);
_cury = RemoteObject.solve(new RemoteObject[] {_cury,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 240)))}, "+",1, 1);Debug.locals.put("curY", _cury);
 };
 };
 BA.debugLineNum = 379;BA.debugLine="Dim lblHeader As Label : lblHeader.Initialize(\"\")";
Debug.ShouldStop(67108864);
_lblheader = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblHeader", _lblheader);
 BA.debugLineNum = 379;BA.debugLine="Dim lblHeader As Label : lblHeader.Initialize(\"\")";
Debug.ShouldStop(67108864);
_lblheader.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 380;BA.debugLine="Dim bHeader As B4XView = lblHeader";
Debug.ShouldStop(134217728);
_bheader = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_bheader = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _lblheader.getObject());Debug.locals.put("bHeader", _bheader);Debug.locals.put("bHeader", _bheader);
 BA.debugLineNum = 381;BA.debugLine="bHeader.Text = \"ΠΕΡΙΓΡΑΦΗ\" ' Description in Greek";
Debug.ShouldStop(268435456);
_bheader.runMethod(true,"setText",BA.ObjectToCharSequence("ΠΕΡΙΓΡΑΦΗ"));
 BA.debugLineNum = 382;BA.debugLine="bHeader.TextColor = 0xFFA89878";
Debug.ShouldStop(536870912);
_bheader.runMethod(true,"setTextColor",BA.numberCast(int.class, ((int)0xffa89878)));
 BA.debugLineNum = 383;BA.debugLine="bHeader.TextSize = 14";
Debug.ShouldStop(1073741824);
_bheader.runMethod(true,"setTextSize",BA.numberCast(float.class, 14));
 BA.debugLineNum = 384;BA.debugLine="bHeader.Font = xui.CreateDefaultBoldFont(14)";
Debug.ShouldStop(-2147483648);
_bheader.runMethod(false,"setFont",main._xui.runMethod(false,"CreateDefaultBoldFont",(Object)(BA.numberCast(float.class, 14))));
 BA.debugLineNum = 385;BA.debugLine="svDetail.Panel.AddView(bHeader, 15dip, curY, 100%";
Debug.ShouldStop(1);
main.mostCurrent._svdetail.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((_bheader.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 15)))),(Object)(_cury),(Object)(RemoteObject.solve(new RemoteObject[] {main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))}, "-",1, 1)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))));
 BA.debugLineNum = 386;BA.debugLine="curY = curY + 35dip";
Debug.ShouldStop(2);
_cury = RemoteObject.solve(new RemoteObject[] {_cury,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 35)))}, "+",1, 1);Debug.locals.put("curY", _cury);
 BA.debugLineNum = 389;BA.debugLine="Dim lblDesc As Label : lblDesc.Initialize(\"\")";
Debug.ShouldStop(16);
_lbldesc = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");Debug.locals.put("lblDesc", _lbldesc);
 BA.debugLineNum = 389;BA.debugLine="Dim lblDesc As Label : lblDesc.Initialize(\"\")";
Debug.ShouldStop(16);
_lbldesc.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 390;BA.debugLine="Dim bDesc As B4XView = lblDesc";
Debug.ShouldStop(32);
_bdesc = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper");
_bdesc = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), _lbldesc.getObject());Debug.locals.put("bDesc", _bdesc);Debug.locals.put("bDesc", _bdesc);
 BA.debugLineNum = 391;BA.debugLine="bDesc.TextColor = xui.Color_LightGray";
Debug.ShouldStop(64);
_bdesc.runMethod(true,"setTextColor",main._xui.getField(true,"Color_LightGray"));
 BA.debugLineNum = 392;BA.debugLine="bDesc.TextSize = 17";
Debug.ShouldStop(128);
_bdesc.runMethod(true,"setTextSize",BA.numberCast(float.class, 17));
 BA.debugLineNum = 393;BA.debugLine="Dim fullText As String = Entry.Get(\"description\")";
Debug.ShouldStop(256);
_fulltext = BA.ObjectToString(_entry.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("description")))));Debug.locals.put("fullText", _fulltext);Debug.locals.put("fullText", _fulltext);
 BA.debugLineNum = 394;BA.debugLine="bDesc.Text = fullText";
Debug.ShouldStop(512);
_bdesc.runMethod(true,"setText",BA.ObjectToCharSequence(_fulltext));
 BA.debugLineNum = 397;BA.debugLine="Dim charCount As Int = fullText.Length";
Debug.ShouldStop(4096);
_charcount = _fulltext.runMethod(true,"length");Debug.locals.put("charCount", _charcount);Debug.locals.put("charCount", _charcount);
 BA.debugLineNum = 398;BA.debugLine="Dim estimatedLines As Int = (charCount / 25) + 5";
Debug.ShouldStop(8192);
_estimatedlines = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_charcount,RemoteObject.createImmutable(25)}, "/",0, 0)),RemoteObject.createImmutable(5)}, "+",1, 0));Debug.locals.put("estimatedLines", _estimatedlines);Debug.locals.put("estimatedLines", _estimatedlines);
 BA.debugLineNum = 399;BA.debugLine="Dim textHeight As Int = estimatedLines * 25dip";
Debug.ShouldStop(16384);
_textheight = RemoteObject.solve(new RemoteObject[] {_estimatedlines,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 25)))}, "*",0, 1);Debug.locals.put("textHeight", _textheight);Debug.locals.put("textHeight", _textheight);
 BA.debugLineNum = 401;BA.debugLine="svDetail.Panel.AddView(bDesc, 15dip, curY, 100%x";
Debug.ShouldStop(65536);
main.mostCurrent._svdetail.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((_bdesc.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 15)))),(Object)(_cury),(Object)(RemoteObject.solve(new RemoteObject[] {main.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),main.mostCurrent.activityBA),main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))}, "-",1, 1)),(Object)(_textheight));
 BA.debugLineNum = 403;BA.debugLine="svDetail.Panel.Height = curY + textHeight + 50dip";
Debug.ShouldStop(262144);
main.mostCurrent._svdetail.runMethod(false,"getPanel").runMethod(true,"setHeight",RemoteObject.solve(new RemoteObject[] {_cury,_textheight,main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))}, "++",2, 1));
 BA.debugLineNum = 404;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _txtsearch_textchanged(RemoteObject _old,RemoteObject _new) throws Exception{
try {
		Debug.PushSubsStack("txtSearch_TextChanged (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,331);
if (RapidSub.canDelegate("txtsearch_textchanged")) { return gr.mythologia.main.remoteMe.runUserSub(false, "main","txtsearch_textchanged", _old, _new);}
Debug.locals.put("Old", _old);
Debug.locals.put("New", _new);
 BA.debugLineNum = 331;BA.debugLine="Sub txtSearch_TextChanged (Old As String, New As S";
Debug.ShouldStop(1024);
 BA.debugLineNum = 332;BA.debugLine="FillList(New)";
Debug.ShouldStop(2048);
_filllist(_new);
 BA.debugLineNum = 333;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}