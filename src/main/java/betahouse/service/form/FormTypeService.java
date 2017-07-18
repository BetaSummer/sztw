package betahouse.service.form;

import betahouse.model.FormType;

import java.util.List;

/**
 * Created by x1654 on 2017/7/17.
 */
public interface FormTypeService {

    FormType getFormTypeByFormType(int formType);

    FormType getFormTypeByFormName(String formName);

    List<FormType> listAll();

}
