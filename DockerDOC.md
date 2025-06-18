* Khi bạn chạy một container với -d, container đó sẽ chạy ngầm, không chiếm màn hình terminal của bạn.
Bạn không thấy log hay giao diện tương tác của nó trực tiếp, nhưng nó vẫn hoạt động bình thường phía sau.

** **
Build image từ Dockerfile:
* docker build -t imageCuong .
Vào bên trong container: 
* docker exec -it <container_id> bash

