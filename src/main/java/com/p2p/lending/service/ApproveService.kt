package com.p2p.lending.service

import com.p2p.lending.entity.Approveitem
import org.springframework.stereotype.Service

/**
 * @Name: ApproveService
 * @Description:璁よ瘉椤硅缃殑Service灞�
 * @author chenqingshan
 * @Date: 2017-2-20 Time: 20:24
 */
@Service
open interface ApproveService {
    /**
     * Description锛氭牴鎹潯浠惰幏鍙栬璇侀」锛屽鏋滄潯浠朵负绌哄垯鏀惧洖鎵�湁璁よ瘉椤�
     *
     * @return List
     */
    fun queryApproves(map: Map<String, Any?>): List<Approveitem>

    /**
     * Description锛氭坊鍔犳柊璁よ瘉椤�
     *
     * @param map
     */
    fun addApproves(map: Map<String, Any?>)

    /**
     * Description锛氫慨鏀硅璇侀」
     *
     * @param map
     */
    fun updateApproves(map: Map<String, Any?>)
}
