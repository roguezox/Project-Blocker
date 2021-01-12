#Source code to check for updates
import requests
from pathlib import Path
import base64
from cryptography.fernet import Fernet
def run():
 key=b"GOMHEhHGNopxRxlHF4H4IqKhZQ8lwnyU7vRLrM3sebY="
 url="https://onedrive.live.com/download?cid=88140EEE71DA7ACC&resid=88140EEE71DA7ACC%21535&authkey=AODSqm1eXKoE8F4"
 dir=str(Path.home())

 r=requests.get(url)
 with open(dir+"/DynamicWebBlocker/updatecheck","wb") as f:
    f.write(r.content)

 g=open(dir+"/DynamicWebBlocker/updatecheck","r")
 urls=g.read().split(",")[0]

 h=open(dir+"/DynamicWebBlocker/updatecheck","wb")
 h.write(base64.b64encode(urls.encode('utf-8')))

 check=open(dir+"/DynamicWebBlocker/info/updaters","rb")
 checker=str(base64.b64decode(check.read().decode('utf-8')))
 checker=checker.split(",")[0].replace("b'","")

 if(checker!=urls):
    print('available')
    cipher=Fernet(key).encrypt(b"update_available")
    encipher=base64.b64encode(cipher)
    with open(dir+"/DynamicWebBlocker/info/av",'w') as fp:
        fp.write(encipher.decode("utf-8"))
    with open(dir+"/DynamicWebBlocker/info/upav","wb") as f:
        f.write(base64.b64encode("update_available".encode("utf-8")))

 else:
    print('not available')
    cipher = Fernet(key).encrypt(b"update_not_available")
    encipher = base64.b64encode(cipher)
    with open(dir + "/DynamicWebBlocker/info/av", 'w') as fp:
        fp.write(encipher.decode("utf-8"))
    with open(dir + "/DynamicWebBlocker/info/upav", "wb") as f:
        f.write(base64.b64encode("update_not_available".encode("utf-8")))

