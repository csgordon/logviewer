#!/bin/bash
export PATH=$CHECKERFRAMEWORK/checker/bin:$PATH

JARS="/homes/gws/csgordon/research/experiments/timed/logviewer/logviewer/de.anbos.eclipse.logviewer.plugin/lib/jakarta-regexp-1.5.jar"

#ECLIPSEJARS="/usr/lib/eclipse/plugins/org.eclipse.ui.navigator_3.5.100.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.ui.ide_3.7.0.dist.jar:/usr/lib/eclipse/dropins/jdt/plugins/org.eclipse.jdt.ui_3.7.0.dist.jar:/usr/lib/eclipse/dropins/jdt/plugins/org.eclipse.jdt.core_3.7.0.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.ui.navigator.resources_3.4.300.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.debug.core_3.7.0.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.debug.ui_3.7.0.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.core.resources_3.7.100.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.swt.gtk.linux.x86_64_3.7.0.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.ui_3.7.0.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.swt_3.7.0.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.jface_3.7.0.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.core.commands_3.6.0.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.ui.workbench_3.7.0.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.core.runtime_3.7.0.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.osgi_3.7.0.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.equinox.common_3.6.0.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.core.jobs_3.5.100.dist.jar:/usr/lib/ecliplse/plugins/org.eclipse.core.runtime.compatibility.registry_3.5.0.dist/runtime_registry_compatibility.jar:/usr/lib/eclipse/plugins/org.eclipse.equinox.registry_3.5.100.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.equinox.preferences_3.4.0.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.core.contenttype_3.4.100.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.equinox.app_1.3.100.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.ui.forms_3.5.100.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.ui.workbench.texteditor_3.7.0.dist.jar:/usr/lib/eclipse/plugins/org.eclipse.core.net_1.2.100.dist.jar"
ECLIPSEJARS="/usr/lib/eclipse/dropins/jdt/plugins/org.eclipse.jdt.debug_3.7.0.dist/jdimodel.jar:`ls /usr/lib/eclipse/plugins/*.jar /usr/lib/eclipse/dropins/jdt/plugins/*.jar | tr "\n" :`:/homes/gws/csgordon/research/experiments/timed/jvmmonitor/jvmmonitor-read-only/org.jvmmonitor.ui/lib/org.swtchart_0.8.0.v20120301.jar"



DEBUG= #"-AprintErrorStack -Afilenames -Ashowchecks" #-Alint=debugSpew"
ERRS=9999

COMMAND="javac -encoding ISO-8859-1 -Xmaxerrs $ERRS -cp $JARS:$ECLIPSEJARS -processor org.checkerframework.checker.guieffect.GuiEffectChecker $DEBUG"

echo $COMMAND

JAVAFILES=`find . -name "*.java"`
#echo $JAVAFILES

pushd ~/research/guitypes/checker && ant dist && popd && find . -name '*.java' | xargs $COMMAND;
