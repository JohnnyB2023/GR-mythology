package gr.mythologia;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class b4xprogressdialog extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new anywheresoftware.b4a.ShellBA(_ba, this, htSubs, "gr.mythologia.b4xprogressdialog");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", gr.mythologia.b4xprogressdialog.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 
    public void  innerInitializeHelper(anywheresoftware.b4a.BA _ba) throws Exception{
        innerInitialize(_ba);
    }
    public Object callSub(String sub, Object sender, Object[] args) throws Exception {
        return BA.SubDelegator.SubNotFound;
    }
public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _mbase = null;
public Object _mtext = null;
public gr.mythologia.b4xloadingindicator _loadingindicator = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _label1 = null;
public gr.mythologia.b4xdialog _mdialog = null;
public b4a.example.dateutils _dateutils = null;
public gr.mythologia.main _main = null;
public gr.mythologia.starter _starter = null;
public gr.mythologia.xuiviewsutils _xuiviewsutils = null;
public anywheresoftware.b4a.objects.B4XViewWrapper  _getpanel(gr.mythologia.b4xprogressdialog __ref,gr.mythologia.b4xdialog _dialog) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xprogressdialog";
if (Debug.shouldDelegate(ba, "getpanel", true))
	 {return ((anywheresoftware.b4a.objects.B4XViewWrapper) Debug.delegate(ba, "getpanel", new Object[] {_dialog}));}
RDebugUtils.currentLine=15532032;
 //BA.debugLineNum = 15532032;BA.debugLine="Public Sub GetPanel (Dialog As B4XDialog) As B4XVi";
RDebugUtils.currentLine=15532033;
 //BA.debugLineNum = 15532033;BA.debugLine="Return mBase";
if (true) return __ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ;
RDebugUtils.currentLine=15532034;
 //BA.debugLineNum = 15532034;BA.debugLine="End Sub";
return null;
}
public String  _show(gr.mythologia.b4xprogressdialog __ref,gr.mythologia.b4xdialog _dialog) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xprogressdialog";
if (Debug.shouldDelegate(ba, "show", true))
	 {return ((String) Debug.delegate(ba, "show", new Object[] {_dialog}));}
RDebugUtils.currentLine=15728640;
 //BA.debugLineNum = 15728640;BA.debugLine="Private Sub Show (Dialog As B4XDialog) 'ignore";
RDebugUtils.currentLine=15728641;
 //BA.debugLineNum = 15728641;BA.debugLine="LoadingIndicator.Show";
__ref._loadingindicator /*gr.mythologia.b4xloadingindicator*/ ._show /*String*/ (null);
RDebugUtils.currentLine=15728642;
 //BA.debugLineNum = 15728642;BA.debugLine="End Sub";
return "";
}
public String  _dialogclosed(gr.mythologia.b4xprogressdialog __ref,int _result) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xprogressdialog";
if (Debug.shouldDelegate(ba, "dialogclosed", true))
	 {return ((String) Debug.delegate(ba, "dialogclosed", new Object[] {_result}));}
RDebugUtils.currentLine=15794176;
 //BA.debugLineNum = 15794176;BA.debugLine="Private Sub DialogClosed(Result As Int) 'ignore";
RDebugUtils.currentLine=15794177;
 //BA.debugLineNum = 15794177;BA.debugLine="LoadingIndicator.Hide";
__ref._loadingindicator /*gr.mythologia.b4xloadingindicator*/ ._hide /*String*/ (null);
RDebugUtils.currentLine=15794178;
 //BA.debugLineNum = 15794178;BA.debugLine="End Sub";
return "";
}
public String  _class_globals(gr.mythologia.b4xprogressdialog __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xprogressdialog";
RDebugUtils.currentLine=15269888;
 //BA.debugLineNum = 15269888;BA.debugLine="Sub Class_Globals";
RDebugUtils.currentLine=15269889;
 //BA.debugLineNum = 15269889;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
RDebugUtils.currentLine=15269890;
 //BA.debugLineNum = 15269890;BA.debugLine="Public mBase As B4XView";
_mbase = new anywheresoftware.b4a.objects.B4XViewWrapper();
RDebugUtils.currentLine=15269891;
 //BA.debugLineNum = 15269891;BA.debugLine="Private mText As Object";
_mtext = new Object();
RDebugUtils.currentLine=15269892;
 //BA.debugLineNum = 15269892;BA.debugLine="Public LoadingIndicator As B4XLoadingIndicator";
_loadingindicator = new gr.mythologia.b4xloadingindicator();
RDebugUtils.currentLine=15269893;
 //BA.debugLineNum = 15269893;BA.debugLine="Public Label1 As B4XView";
_label1 = new anywheresoftware.b4a.objects.B4XViewWrapper();
RDebugUtils.currentLine=15269894;
 //BA.debugLineNum = 15269894;BA.debugLine="Public mDialog As B4XDialog";
_mdialog = new gr.mythologia.b4xdialog();
RDebugUtils.currentLine=15269895;
 //BA.debugLineNum = 15269895;BA.debugLine="End Sub";
return "";
}
public Object  _gettext(gr.mythologia.b4xprogressdialog __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xprogressdialog";
if (Debug.shouldDelegate(ba, "gettext", true))
	 {return ((Object) Debug.delegate(ba, "gettext", null));}
RDebugUtils.currentLine=15466496;
 //BA.debugLineNum = 15466496;BA.debugLine="Public Sub getText As Object";
RDebugUtils.currentLine=15466497;
 //BA.debugLineNum = 15466497;BA.debugLine="Return mText";
if (true) return __ref._mtext /*Object*/ ;
RDebugUtils.currentLine=15466498;
 //BA.debugLineNum = 15466498;BA.debugLine="End Sub";
return null;
}
public String  _hide(gr.mythologia.b4xprogressdialog __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xprogressdialog";
if (Debug.shouldDelegate(ba, "hide", true))
	 {return ((String) Debug.delegate(ba, "hide", null));}
RDebugUtils.currentLine=15663104;
 //BA.debugLineNum = 15663104;BA.debugLine="Public Sub Hide";
RDebugUtils.currentLine=15663105;
 //BA.debugLineNum = 15663105;BA.debugLine="mDialog.Close(0)";
__ref._mdialog /*gr.mythologia.b4xdialog*/ ._close /*boolean*/ (null,(int) (0));
RDebugUtils.currentLine=15663106;
 //BA.debugLineNum = 15663106;BA.debugLine="End Sub";
return "";
}
public String  _initialize(gr.mythologia.b4xprogressdialog __ref,anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.B4XViewWrapper _parent) throws Exception{
__ref = this;
innerInitialize(_ba);
RDebugUtils.currentModule="b4xprogressdialog";
if (Debug.shouldDelegate(ba, "initialize", true))
	 {return ((String) Debug.delegate(ba, "initialize", new Object[] {_ba,_parent}));}
RDebugUtils.currentLine=15335424;
 //BA.debugLineNum = 15335424;BA.debugLine="Public Sub Initialize (Parent As B4XView)";
RDebugUtils.currentLine=15335425;
 //BA.debugLineNum = 15335425;BA.debugLine="mBase = xui.CreatePanel(\"mBase\")";
__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/  = __ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .CreatePanel(ba,"mBase");
RDebugUtils.currentLine=15335426;
 //BA.debugLineNum = 15335426;BA.debugLine="mBase.SetLayoutAnimated(0, 0, 0, 300dip, 60dip)";
__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated((int) (0),(int) (0),(int) (0),__c.DipToCurrent((int) (300)),__c.DipToCurrent((int) (60)));
RDebugUtils.currentLine=15335427;
 //BA.debugLineNum = 15335427;BA.debugLine="mBase.LoadLayout(\"XV_ProgressTemplate\")";
__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .LoadLayout("XV_ProgressTemplate",ba);
RDebugUtils.currentLine=15335428;
 //BA.debugLineNum = 15335428;BA.debugLine="mBase.SetColorAndBorder(xui.Color_White, 0, 0, 5d";
__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetColorAndBorder(__ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .Color_White,(int) (0),(int) (0),__c.DipToCurrent((int) (5)));
RDebugUtils.currentLine=15335429;
 //BA.debugLineNum = 15335429;BA.debugLine="LoadingIndicator.Hide";
__ref._loadingindicator /*gr.mythologia.b4xloadingindicator*/ ._hide /*String*/ (null);
RDebugUtils.currentLine=15335430;
 //BA.debugLineNum = 15335430;BA.debugLine="mDialog.Initialize(Parent)";
__ref._mdialog /*gr.mythologia.b4xdialog*/ ._initialize /*String*/ (null,ba,_parent);
RDebugUtils.currentLine=15335431;
 //BA.debugLineNum = 15335431;BA.debugLine="mDialog.ButtonsHeight = -2dip";
__ref._mdialog /*gr.mythologia.b4xdialog*/ ._buttonsheight /*int*/  = (int) (-__c.DipToCurrent((int) (2)));
RDebugUtils.currentLine=15335432;
 //BA.debugLineNum = 15335432;BA.debugLine="mDialog.BorderWidth = 0";
__ref._mdialog /*gr.mythologia.b4xdialog*/ ._borderwidth /*int*/  = (int) (0);
RDebugUtils.currentLine=15335433;
 //BA.debugLineNum = 15335433;BA.debugLine="mDialog.BorderCornersRadius = 5dip";
__ref._mdialog /*gr.mythologia.b4xdialog*/ ._bordercornersradius /*int*/  = __c.DipToCurrent((int) (5));
RDebugUtils.currentLine=15335434;
 //BA.debugLineNum = 15335434;BA.debugLine="End Sub";
return "";
}
public String  _settext(gr.mythologia.b4xprogressdialog __ref,Object _t) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xprogressdialog";
if (Debug.shouldDelegate(ba, "settext", true))
	 {return ((String) Debug.delegate(ba, "settext", new Object[] {_t}));}
RDebugUtils.currentLine=15400960;
 //BA.debugLineNum = 15400960;BA.debugLine="Public Sub setText(t As Object)";
RDebugUtils.currentLine=15400961;
 //BA.debugLineNum = 15400961;BA.debugLine="XUIViewsUtils.SetTextOrCSBuilderToLabel(Label1, t";
_xuiviewsutils._settextorcsbuildertolabel /*String*/ (ba,__ref._label1 /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ,_t);
RDebugUtils.currentLine=15400962;
 //BA.debugLineNum = 15400962;BA.debugLine="End Sub";
return "";
}
public String  _showdialog(gr.mythologia.b4xprogressdialog __ref,Object _text) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xprogressdialog";
if (Debug.shouldDelegate(ba, "showdialog", true))
	 {return ((String) Debug.delegate(ba, "showdialog", new Object[] {_text}));}
RDebugUtils.currentLine=15597568;
 //BA.debugLineNum = 15597568;BA.debugLine="Public Sub ShowDialog (Text As Object)";
RDebugUtils.currentLine=15597569;
 //BA.debugLineNum = 15597569;BA.debugLine="setText(Text)";
__ref._settext /*String*/ (null,_text);
RDebugUtils.currentLine=15597570;
 //BA.debugLineNum = 15597570;BA.debugLine="If mDialog.Visible = False Then";
if (__ref._mdialog /*gr.mythologia.b4xdialog*/ ._getvisible /*boolean*/ (null)==__c.False) { 
RDebugUtils.currentLine=15597571;
 //BA.debugLineNum = 15597571;BA.debugLine="mDialog.ShowTemplate(Me, \"\", \"\", \"\")";
__ref._mdialog /*gr.mythologia.b4xdialog*/ ._showtemplate /*anywheresoftware.b4a.keywords.Common.ResumableSubWrapper*/ (null,this,(Object)(""),(Object)(""),(Object)(""));
 };
RDebugUtils.currentLine=15597573;
 //BA.debugLineNum = 15597573;BA.debugLine="End Sub";
return "";
}
}