#Source code to download updates
import requests
from pathlib import Path
import base64
from cryptography.fernet import Fernet
def run():
 dir = str(Path.home())
 key=b"GOMHEhHGNopxRxlHF4H4IqKhZQ8lwnyU7vRLrM3sebY="
 g = open(dir + "/DynamicWebBlocker/info/av", 'r')
 encipher = base64.b64decode(g.read())
 normal = Fernet(key).decrypt(encipher)
 print(normal.decode("utf-8"))
 if (normal.decode("utf-8") == "update_available"):
    print("downloading")
    url = "https://onedrive.live.com/download?cid=88140EEE71DA7ACC&resid=88140EEE71DA7ACC%21445&authkey=APh3EzCzsbkDIy0"
    r = requests.get(url)

    with open(dir + "/DynamicWebBlocker/info/updaters", 'wb') as f:
        f.write(r.content)

    g = open(dir + "/DynamicWebBlocker/info/updaters", 'r')
    updateurl = g.read()

    print('downloading updates')

    updates = requests.get(updateurl.split(",")[0])
    with open(dir + updateurl.split(",")[1], "wb") as fp:
        fp.write(updates.content)

    print('updates downloaded')

    h = open(dir + "/DynamicWebBlocker/info/updaters", 'wb')
    h.write(base64.b64encode(updateurl.encode("utf-8")))


 else:
    print("no updates")
