package betahouse.service.form;

import betahouse.mapper.FormTypeMapper;
import betahouse.model.FormType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by x1654 on 2017/7/17.
 */
@Service
public class FormTypeServiceImpl implements FormTypeService{

    @Autowired
    private FormTypeMapper formTypeMapper;

    @Override
    public FormType getFormTypeByFormType(int formType) {
        return formTypeMapper.selectByFormType(formType);
    }

    @Override
    public FormType getFormTypeByFormName(String formName) {
        return formTypeMapper.selectByFormName(formName);
    }

    @Override
    public List<FormType> listAll() {
        return formTypeMapper.selectAll();
    }

}
