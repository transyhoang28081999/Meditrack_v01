package com.example.backend.securities.auth;

import com.example.backend.securities.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Trả về danh sách các quyền của người dùng (ví dụ: ROLE_USER, ROLE_ADMIN)
        // Đây là phần mà bạn cần triển khai dựa trên quyền của người dùng trong hệ thống của bạn
        return null;
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Kiểm tra tình trạng tài khoản có hết hạn hay không
        // Trả về true nếu tài khoản không hết hạn
        // Trong trường hợp này, bạn có thể trả về giá trị mặc định hoặc triển khai logic riêng của bạn
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Kiểm tra tình trạng tài khoản có bị khóa hay không
        // Trả về true nếu tài khoản không bị khóa
        // Trong trường hợp này, bạn có thể trả về giá trị mặc định hoặc triển khai logic riêng của bạn
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Kiểm tra tình trạng thông tin xác thực của người dùng có hết hạn hay không
        // Trả về true nếu thông tin xác thực không hết hạn
        // Trong trường hợp này, bạn có thể trả về giá trị mặc định hoặc triển khai logic riêng của bạn
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Kiểm tra tình trạng người dùng có được kích hoạt hay không
        // Trả về true nếu người dùng đã được kích hoạt
        // Trong trường hợp này, bạn có thể trả về giá trị mặc định hoặc triển khai logic riêng của bạn
        return true;
    }
}
