# MedicineManagement #
Bản thử nghiệm cho sinh viên Bách Khoa

### Cách sử dụng ###

- add các lib vào trong project (việc này tùy vào ide đang sử dụng) :
Với Intellij thì project structure  -> Library -> + 

- Nếu gặp lỗi về javafx khi đã add: 
![image](https://user-images.githubusercontent.com/81417323/178755195-d7cefc8f-2480-459f-9ed8-9a4fbb42d516.png)  <br />
-> thêm VM OPTIONS 
``` --module-path /path/to/JavaFX/lib --add-modules=javafx.controls ```

Đến đây coi như việc cài đặt đã hoàn thành 


### Công nghệ và công cụ đã sử dụng ###
- Apache POI : đọc và ghi file excel (lưu, đọc dữ liệu)
- Jsoup : đùng để crawl data từ website 
- Gson : dùng để đọc file json (trong quá trình crawl phát sinh)
- JavaFX + Scene Builder: xây dựng UI cho chương trình

### Mô hình sử dụng ###
MVC (Model - View - Controller)

### Thiết kế packet ###
- packet ```java``` chứa các packet khác:
  + packet ``` model ``` gồm các file java chứa thực thể cùng với các get/set 
  + packet ``` controller ``` gồm các file java điều kiển các file .fxml và model
  + packet ``` DAO ``` chứa các hàm để giao tiếp với file excel
  + packet ``` service ``` gồm các hàm để crawl data từ trang web ( phục vụ cho phần tin tức và thông tin product)
  + packet ``` interface ``` gồm các interface tự định nghĩa trong quá trình lập trình
- packet ``` resource ``` :
  + packet ``` view ``` gồm các file .fxml + .css
  + packet ``` asset ``` gồm ảnh và icon
