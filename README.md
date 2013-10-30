android-volley-duplicateheadersfix
==================================

Fix for this Stack Overflow question:
http://stackoverflow.com/questions/18998361/android-volley-duplicate-set-cookie-is-overriden
And discussed on the official Volley group over here:
https://groups.google.com/forum/#!topic/volley-users/rNTlV-LORzY

Android Volley returns response headers as a Map which overwrites duplicate keys. So you only get one 'Set-Cookie' header for example. I attempted to fix this by extending some classes but once I had to extend NetworkResponse, it proved too troublesome. So I just made edits to
src/com/android/volley/NetworkResponse.java, 
