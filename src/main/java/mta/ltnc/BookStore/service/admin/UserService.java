package mta.ltnc.BookStore.service.admin;

import mta.ltnc.BookStore.constant.*;
import mta.ltnc.BookStore.dto.*;
import mta.ltnc.BookStore.entity.User;
import mta.ltnc.BookStore.repositories.RoleRepository;
import mta.ltnc.BookStore.repositories.UserGroupRepository;
import mta.ltnc.BookStore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;


    @Autowired
    private RoleRepository roleRepository;

    private String validateCreateAccount(User user) {
        User check;
        check = userRepository.findByUserName(user.getUserName());
        if (check != null) return MessageConstants.ACCOUNT_EXIST;
        check = userRepository.findByEmail(user.getEmail());
        if (check != null) return MessageConstants.EMAIL_EXIST;
        return null;
    }

    private String validateUpdateAccount(User user) {
        User check;
        check = userRepository.findByEmail(user.getEmail());
        if (check != null && check.getId() != user.getId()) return MessageConstants.EMAIL_EXIST;
        return null;
    }

    private boolean isAccountInRole(String role, User user) {
        if (role.equals(user.getUserGroup().getName())) {
            return true;
        }
        return false;
    }

    public UserDTO getAccount(Long id) {
        User user = userRepository.findById(id).get();
        UserDTO result = new UserDTO();
//        Employee employee = employeeRepository.findByAccount(account);
//        Customer customer = customerRepository.findByAccount(account);

        if (isAccountInRole(AuthoritiesConstants.ADMIN, user)) {
            result.setRole(AuthoritiesConstants.ADMIN);
        } else {
            if (isAccountInRole(AuthoritiesConstants.EMPLOYEE, user)){
                result.setRole(AuthoritiesConstants.EMPLOYEE);
            } else {
                if (isAccountInRole(AuthoritiesConstants.CHECKER, user))
                    result.setRole(AuthoritiesConstants.CHECKER);
                else result.setRole(AuthoritiesConstants.CUSTOMER);
            }
        }
        result.setUser(user);
        return result;
//        result.setEmployee(employee);
//        result.setCustomer(customer);
//        return new ResponseDto("0", "Success", result);
    }

//    public ResponseDto createAccount(AccountDto dto) {
//        String msg = validateCreateAccount(dto.getAccount());
//        if (msg != null) return new ResponseDto("1", msg, null);
//
//        AccountDto result = new AccountDto();
//        Authority authAdmin = authorityRepository.findByName(AuthoritiesConstants.ADMIN);
//        Authority authEmployee = authorityRepository.findByName(AuthoritiesConstants.EMPLOYEE);
//        Authority authCustomer = authorityRepository.findByName(AuthoritiesConstants.CUSTOMER);
//        Authority authChecker = authorityRepository.findByName(AuthoritiesConstants.CHECKER);
//
//        Account account = dto.getAccount();
//        account.setPassword(passwordEncoder.encode(account.getPassword()));
//        account.setCreatedDate((new Date()).getTime());
//
//        HashSet<Authority> listAuthorities = new HashSet<>();
//        Account savedAccount;
//        Employee savedEmployee = new Employee();
//        Customer savedCustomer = new Customer();
//
//        Employee employee = new Employee();
//        Customer customer = new Customer();
//
//        switch (dto.getRole()) {
//            case AuthoritiesConstants.ADMIN: {
//                listAuthorities.add(authAdmin);
//                listAuthorities.add(authEmployee);
//                listAuthorities.add(authCustomer);
//                listAuthorities.add(authChecker);
//                account.setAuthorities(listAuthorities);
//                savedAccount = accountRepository.save(account);
//                break;
//            }
//            case AuthoritiesConstants.EMPLOYEE: {
//                listAuthorities.add(authEmployee);
//                listAuthorities.add(authCustomer);
//                account.setAuthorities(listAuthorities);
//                savedAccount = accountRepository.save(account);
//                employee.setAccount(savedAccount);
//                employee.setBookStore(dto.getEmployee().getBookStore());
//                employee.setGender(dto.getEmployee().getGender());
//                employee.setName(dto.getEmployee().getName());
//                employee.setPhone(dto.getEmployee().getPhone());
//                employee.setWorkShift(dto.getEmployee().getWorkShift());
//                savedEmployee = employeeRepository.save(employee);
//                break;
//            }
//            case AuthoritiesConstants.CUSTOMER: {
//                Customer c = customerRepository.findByPhone(dto.getCustomer().getPhone());
//                if (c != null) return new ResponseDto("1", MessageConstants.PHONE_EXIST, null);
//                listAuthorities.add(authCustomer);
//                account.setAuthorities(listAuthorities);
//                savedAccount = accountRepository.save(account);
//                customer.setAccount(savedAccount);
//                customer.setPhone(dto.getCustomer().getPhone());
//                customer.setName(dto.getCustomer().getName());
//                customer.setGender(dto.getCustomer().getGender());
//                savedCustomer = customerRepository.save(customer);
//                break;
//            }
//            case AuthoritiesConstants.CHECKER: {
//                listAuthorities.add(authChecker);
//                listAuthorities.add(authCustomer);
//                account.setAuthorities(listAuthorities);
//                savedAccount = accountRepository.save(account);
//                employee.setAccount(savedAccount);
//                employee.setGender(dto.getEmployee().getGender());
//                employee.setName(dto.getEmployee().getName());
//                employee.setPhone(dto.getEmployee().getPhone());
//                savedEmployee = employeeRepository.save(employee);
//                break;
//            }
//            default: {
//                return new ResponseDto("1", MessageConstants.ERROR, null);
//            }
//        }
//        result.setRole(dto.getRole());
//        result.setAccount(savedAccount);
//        result.setEmployee(savedEmployee);
//        result.setCustomer(savedCustomer);
//        return new ResponseDto("0", MessageConstants.ACCOUNT_CREATED, result);
//    }
//
//    public ResponseDto updateAccount(AccountDto dto) {
//        String msg = validateUpdateAccount(dto.getAccount());
//        if (msg != null) return new ResponseDto("1", msg, null);
//
//        Account old = accountRepository.findById(dto.getAccount().getId()).get();
//        Account update = dto.getAccount();
//        update.setPassword(old.getPassword());
//
//        Account savedAccount = accountRepository.save(update);
//        Employee savedEmployee = new Employee();
//        Customer savedCustomer = new Customer();
//
//
//        switch (dto.getRole()) {
//            case AuthoritiesConstants.EMPLOYEE: {
//                savedEmployee = employeeRepository.save(dto.getEmployee());
//                break;
//            }
//            case AuthoritiesConstants.CUSTOMER: {
//                savedCustomer = customerRepository.save(dto.getCustomer());
//                break;
//            }
//            case AuthoritiesConstants.CHECKER: {
//                savedEmployee = employeeRepository.save(dto.getEmployee());
//                break;
//            }
//            default: {
//                break;
//            }
//        }
//        AccountDto result = new AccountDto();
//        result.setRole(dto.getRole());
//        result.setAccount(savedAccount);
//        result.setEmployee(savedEmployee);
//        result.setCustomer(savedCustomer);
//        return new ResponseDto("0", MessageConstants.ACCOUNT_UPDATED, result);
//    }
//
//    public ResponseDto changePassword(ChangePasswordDto dto) {
//        Account account = SecurityUtils.getCurrentUser();
//
//        if (passwordEncoder.matches(dto.getOldPassword(), account.getPassword())) {
//            account.setPassword(passwordEncoder.encode(dto.getNewPassword()));
//            accountRepository.save(account);
//            return new ResponseDto("0", MessageConstants.PASSWORD_CHANGED, null);
//        }
//        return new ResponseDto("1", MessageConstants.INCORRECT_OLD_PASS, null);
//    }
    public List<UserDTO> getAllAccount() {
        List<User> list = userRepository.findAll();
        List<UserDTO> results = new ArrayList<>();
        for (User user : list) {
            UserDTO dto = new UserDTO();
            dto.setUser(user);
            if (isAccountInRole(AuthoritiesConstants.ADMIN, user)) {
                dto.setRole(AuthoritiesConstants.ADMIN);
            } else {
                if (isAccountInRole(AuthoritiesConstants.EMPLOYEE, user)) {
                    dto.setRole(AuthoritiesConstants.EMPLOYEE);
                } else {
                    if (isAccountInRole(AuthoritiesConstants.CHECKER, user)) {
                        dto.setRole(AuthoritiesConstants.CHECKER);
                    } else {
                        dto.setRole(AuthoritiesConstants.CUSTOMER);
                    }
                }
            }
            results.add(dto);
        }
        return results;
    }
}
