from pathlib import Path
import os
import shutil
curdir=str(Path.cwd())
homedir=str(Path.home())
os.chdir(curdir)
os.system("pip install -r requirements.txt")
os.system("cp -r "+curdir+"/DynamicWebBlocker "+homedir+"/")
os.system("cp -r "+curdir+"/DynamicWebBlocker "+homedir+"/")
shutil.copyfile(curdir+"/output",homedir+"/output")
deskfile="[Desktop Entry]"+"\n"+"Version=1.0"+"\n"+"Name[en_US]=Blocker"+"\n"+"Exec="+homedir+"/DynamicWebBlocker/blockfx.run"+"\n"+"Path="+homedir+"/\n"+"Icon="+homedir+"/DynamicWebBlocker/ui_files/icon.png"+"\n"+"Terminal=false"+"\n"+"Type=Application\n"

g=open(curdir+"/blocker.desktop","w")
g.write(deskfile)

