package test.seleniumhq;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springrain.frame.util.Finder;
import org.springrain.selenium.entity.Htmlcase;
import org.springrain.selenium.entity.Htmlfield;
import org.springrain.selenium.entity.Htmlfunction;
import org.springrain.selenium.entity.Validaterule;
import org.springrain.selenium.service.IHtmlcaseService;
import org.springrain.selenium.service.IHtmlfieldService;
import org.springrain.selenium.service.IHtmlfunctionService;
import org.springrain.selenium.service.IValidateruleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SaveLoginFunctionTest {

    String funcitonId = "testlogin";
    String ctx = "http://127.0.0.1:8080/springrain";

    @Resource
    private IHtmlfunctionService htmlfunctionService;
    
    @Resource
    private IHtmlfieldService htmlfieldService;
    
    @Resource
    private IValidateruleService validateruleService;
    
    @Resource
    private IHtmlcaseService htmlcaseService;
    
    
    
    @Before
    public void testClear() throws Exception {
        Finder htmlfunctionFinder=Finder.getDeleteFinder(Htmlfunction.class);
        htmlfunctionService.update(htmlfunctionFinder);
        
        
        Finder htmlfieldFinder=Finder.getDeleteFinder(Htmlfield.class);
        htmlfieldService.update(htmlfieldFinder);
        
        Finder validateruleFinder=Finder.getDeleteFinder(Validaterule.class);
        validateruleService.update(validateruleFinder);
        
        
        Finder htmlcaseFinder=Finder.getDeleteFinder(Htmlcase.class);
        htmlcaseService.update(htmlcaseFinder);
        
        
        
    }
    
    
    

    @Test
    public void testSave() throws Exception {

        // 新增功能
        Htmlfunction hf = new Htmlfunction();
        hf.setId(funcitonId);
        hf.setName("测试登陆功能");
        hf.setBtnId("sbtButton");
        hf.setUrl(ctx + "/system/login");
        hf.setSortno(1);
        htmlfunctionService.save(hf);



        // 新增字段
        // 登陆需要的字段

        // 账户字段
        Htmlfield account = new Htmlfield();
        account.setFunctionId(funcitonId);
        account.setName("账号");
        account.setHtmlId("account");
        account.setHtmlName("account");
        account.setHtmlFieldType(1);
        account.setHtmlFieldLength(20);
        htmlfieldService.save(account);

        // 密码字段
        Htmlfield password = new Htmlfield();
        password.setFunctionId(funcitonId);
        password.setName("密码");
        password.setHtmlId("password");
        password.setHtmlName("password");
        password.setHtmlFieldType(2);
        password.setHtmlFieldLength(20);
        htmlfieldService.save(password);
        
        
        
        //添加 账户字段 的规则

        //账号为空
        Validaterule accountv3 = new Validaterule();
        accountv3.setId("accountv3");
        accountv3.setFieldId(account.getId());
        accountv3.setResultType(3);
        accountv3.setFindType(3);
        accountv3.setElementKey("msg");
        accountv3.setCompare("eq");
        accountv3.setResultValue("账号不能为空!");
        validateruleService.save(accountv3);
        
        //账号为空的测试用例
        Htmlcase hcaccount1=new Htmlcase();
        hcaccount1.setCaseCode("hcaccount1");
        hcaccount1.setFuctionId(funcitonId);
        hcaccount1.setRuleId(accountv3.getId());
        hcaccount1.setHtmlFieldId(account.getHtmlId());
        hcaccount1.setHtmlFieldName(account.getHtmlName());
        hcaccount1.setHtmlFieldValue("");
        hcaccount1.setSuccessResult(accountv3.getResultValue());
        hcaccount1.setPass(0);
        hcaccount1.setSortno(1);
        htmlcaseService.save(hcaccount1);
        
        
        //账号不存在
        Validaterule accountv6 = new Validaterule();
        accountv6.setId("accountv6");
        accountv6.setFieldId(account.getId());
        accountv6.setResultType(6);
        accountv6.setFindType(3);
        accountv6.setElementKey("msg");
        accountv6.setCompare("eq");
        accountv6.setResultValue("账号错误!");
        validateruleService.save(accountv6);
        
        //账号不存在的测试用例
        Htmlcase hcaccount2=new Htmlcase();
        hcaccount2.setCaseCode("hcaccount2");
        hcaccount2.setFuctionId(funcitonId);
        hcaccount2.setRuleId(accountv6.getId());
        hcaccount2.setHtmlFieldId(account.getHtmlId());
        hcaccount2.setHtmlFieldName(account.getHtmlName());
        hcaccount2.setHtmlFieldValue("1");
        hcaccount2.setSuccessResult(accountv6.getResultValue());
        hcaccount2.setPass(0);
        hcaccount2.setSortno(2);
        htmlcaseService.save(hcaccount2);
        
        
        //账号正确
        Validaterule accountv7 = new Validaterule();
        accountv7.setId("accountv7");
        accountv7.setFieldId(account.getId());
        accountv7.setResultType(7);
        accountv7.setFindType(3);
        accountv7.setElementKey("msg");
        accountv7.setCompare("eq");
        accountv7.setResultValue("密码不能为空!");
        validateruleService.save(accountv7);
        
        //账号不存在的测试用例
        Htmlcase hcaccount3=new Htmlcase();
        hcaccount3.setCaseCode("hcaccount3");
        hcaccount3.setFuctionId(funcitonId);
        hcaccount3.setRuleId(accountv7.getId());
        hcaccount3.setHtmlFieldId(account.getHtmlId());
        hcaccount3.setHtmlFieldName(account.getHtmlName());
        hcaccount3.setHtmlFieldValue("admin");
        hcaccount3.setSuccessResult(accountv7.getResultValue());
        hcaccount3.setPass(0);
        hcaccount3.setSortno(3);
        htmlcaseService.save(hcaccount3);
        
        
        
        
        
        //添加 密码字段 的规则

        //密码为空
        Validaterule passwordv3 = new Validaterule();
        passwordv3.setId("passwordv3");
        passwordv3.setFieldId(password.getId());
        passwordv3.setResultType(3);
        passwordv3.setFindType(3);
        passwordv3.setElementKey("msg");
        passwordv3.setCompare("eq");
        passwordv3.setResultValue("密码不能为空!");
        validateruleService.save(passwordv3);
        
        //密码为空的测试用例
        Htmlcase hcpassword1=new Htmlcase();
        hcpassword1.setCaseCode("hcpassword1");
        hcpassword1.setFuctionId(funcitonId);
        hcpassword1.setRuleId(passwordv3.getId());
        hcpassword1.setHtmlFieldId(password.getHtmlId());
        hcpassword1.setHtmlFieldName(password.getHtmlName());
        hcpassword1.setHtmlFieldValue("");
        hcpassword1.setSuccessResult(passwordv3.getResultValue());
        hcpassword1.setPass(0);
        hcpassword1.setSortno(4);
        htmlcaseService.save(hcpassword1);
        
        
        //密码错误
        Validaterule passwordv6 = new Validaterule();
        passwordv6.setId("passwordv6");
        passwordv6.setFieldId(password.getId());
        passwordv6.setResultType(6);
        passwordv6.setFindType(3);
        passwordv6.setElementKey("msg");
        passwordv6.setCompare("eq");
        passwordv6.setResultValue("密码错误!");
        validateruleService.save(passwordv6);
        
        //密码不存在的测试用例
        Htmlcase hcpassword2=new Htmlcase();
        hcpassword2.setCaseCode("hcpassword2");
        hcpassword2.setFuctionId(funcitonId);
        hcpassword2.setRuleId(passwordv6.getId());
        hcpassword2.setHtmlFieldId(password.getHtmlId());
        hcpassword2.setHtmlFieldName(password.getHtmlName());
        hcpassword2.setHtmlFieldValue("1");
        hcpassword2.setSuccessResult(passwordv6.getResultValue());
        hcpassword2.setPass(0);
        hcpassword2.setSortno(5);
        htmlcaseService.save(hcpassword2);
        
        
        //密码正确,登陆成功
        Validaterule passwordv7 = new Validaterule();
        passwordv7.setId("passwordv7");
        passwordv7.setFieldId(password.getId());
        passwordv7.setResultType(1);
        passwordv7.setFindType(0);
        passwordv7.setElementKey("title");
        passwordv7.setCompare("eq");
        passwordv7.setResultValue("后台管理系统");
        validateruleService.save(passwordv7);
        
        //账号准确,登陆成功
        Htmlcase hcpassword3=new Htmlcase();
        hcpassword3.setCaseCode("hcpassword3");
        hcpassword3.setFuctionId(funcitonId);
        hcpassword3.setRuleId(passwordv7.getId());
        hcpassword3.setHtmlFieldId(password.getHtmlId());
        hcpassword3.setHtmlFieldName(password.getHtmlName());
        hcpassword3.setHtmlFieldValue("admin");
        hcpassword3.setSuccessResult(passwordv7.getResultValue());
        hcpassword3.setPass(0);
        hcpassword3.setSortno(6);
        htmlcaseService.save(hcpassword3);
        
        
        
        
        
        
        
    }
}
