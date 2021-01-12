from pathlib import Path
import os
dirname = str(Path.home())
os.popen(dirname+"/DynamicWebBlocker/block_functions/proxy -p 1330")
