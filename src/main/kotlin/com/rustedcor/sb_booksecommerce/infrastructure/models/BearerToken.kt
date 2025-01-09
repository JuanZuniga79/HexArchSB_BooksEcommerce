package com.rustedcor.sb_booksecommerce.infrastructure.models

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.authority.AuthorityUtils

class BearerToken(val value: String) : AbstractAuthenticationToken(AuthorityUtils.NO_AUTHORITIES){
    override fun getCredentials(): Any = value;
    override fun getPrincipal(): Any = value;
}