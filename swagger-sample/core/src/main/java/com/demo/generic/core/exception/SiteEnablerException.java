package com.demo.generic.core.exception;

/**
 * Wrapper business exception used for any errors related to templating.
 *
 * @author avinash
 */
public class SiteEnablerException extends BaseSiteCreatorException {

    public SiteEnablerException(ErrorCodes.Exception_Codes errCode1, Object... params1) {
        super(errCode1,params1);
    }

    public SiteEnablerException(ErrorCodes.Exception_Codes errCode1) {
        super(errCode1);
    }
}
