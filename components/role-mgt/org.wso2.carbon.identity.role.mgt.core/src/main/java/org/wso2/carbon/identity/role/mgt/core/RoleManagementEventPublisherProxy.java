/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.com).
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.role.mgt.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.event.IdentityEventConstants;
import org.wso2.carbon.identity.event.IdentityEventException;
import org.wso2.carbon.identity.event.event.Event;
import org.wso2.carbon.identity.event.services.IdentityEventService;
import org.wso2.carbon.identity.role.mgt.core.internal.RoleManagementServiceComponentHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class handles creating event and publishing events related to role management.
 */
public class RoleManagementEventPublisherProxy {

    private static final Log log = LogFactory.getLog(RoleManagementEventPublisherProxy.class);
    private static final RoleManagementEventPublisherProxy proxy = new RoleManagementEventPublisherProxy();

    private RoleManagementEventPublisherProxy() {

    }

    public static RoleManagementEventPublisherProxy getInstance() {

        return proxy;
    }

    public void publishPreAddRole(String roleName, List<String> userList, List<String> groupList,
                                  List<String> permissions, String tenantDomain) {

        try {
            publishPreAddRoleWithException(roleName, userList, groupList, permissions, tenantDomain);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreAddRoleWithException(String roleName, List<String> userList, List<String> groupList,
                                  List<String> permissions, String tenantDomain)
            throws IdentityRoleManagementException {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_NAME, roleName);
        eventProperties.put(IdentityEventConstants.EventProperty.USER_LIST, userList);
        eventProperties.put(IdentityEventConstants.EventProperty.GROUP_LIST, groupList);
        eventProperties.put(IdentityEventConstants.EventProperty.PERMISSIONS, permissions);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.PRE_ADD_ROLE_EVENT);
        doPublishEvent(event);
    }


    public void publishPostAddRole(String roleName, List<String> userList, List<String> groupList,
                                  List<String> permissions, String tenantDomain) {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_NAME, roleName);
        eventProperties.put(IdentityEventConstants.EventProperty.USER_LIST, userList);
        eventProperties.put(IdentityEventConstants.EventProperty.GROUP_LIST, groupList);
        eventProperties.put(IdentityEventConstants.EventProperty.PERMISSIONS, permissions);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.POST_ADD_ROLE_EVENT);
        try {
            doPublishEvent(event);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreGetRoles(Integer limit, Integer offset, String sortBy, String sortOrder,
                                   String tenantDomain) {

        try {
            publishPreGetRolesWithException(limit, offset, sortBy, sortOrder, tenantDomain);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreGetRolesWithException(Integer limit, Integer offset, String sortBy, String sortOrder,
                                                String tenantDomain) throws IdentityRoleManagementException {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.LIMIT, limit);
        eventProperties.put(IdentityEventConstants.EventProperty.OFFSET, offset);
        eventProperties.put(IdentityEventConstants.EventProperty.SORT_BY, sortBy);
        eventProperties.put(IdentityEventConstants.EventProperty.SORT_ORDER, sortOrder);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.PRE_GET_ROLES_EVENT);
        doPublishEvent(event);
    }

    public void publishPostGetRoles(Integer limit, Integer offset, String sortBy, String sortOrder,
                                    String tenantDomain) {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.LIMIT, limit);
        eventProperties.put(IdentityEventConstants.EventProperty.OFFSET, offset);
        eventProperties.put(IdentityEventConstants.EventProperty.SORT_BY, sortBy);
        eventProperties.put(IdentityEventConstants.EventProperty.SORT_ORDER, sortOrder);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.POST_GET_ROLES_EVENT);
        try {
            doPublishEvent(event);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreGetRoles(String filter, Integer limit, Integer offset, String sortBy, String sortOrder,
                                 String tenantDomain) {

        try {
            publishPreGetRolesWithException(filter, limit, offset, sortBy, sortOrder, tenantDomain);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }



    public void publishPreGetRolesWithException(String filter, Integer limit, Integer offset, String sortBy,
                                                String sortOrder, String tenantDomain)
            throws IdentityRoleManagementException {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.FILTER, filter);
        eventProperties.put(IdentityEventConstants.EventProperty.LIMIT, limit);
        eventProperties.put(IdentityEventConstants.EventProperty.OFFSET, offset);
        eventProperties.put(IdentityEventConstants.EventProperty.SORT_BY, sortBy);
        eventProperties.put(IdentityEventConstants.EventProperty.SORT_ORDER, sortOrder);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.PRE_GET_ROLES_EVENT);
        doPublishEvent(event);
    }

    public void publishPostGetRoles(String filter, Integer limit, Integer offset, String sortBy, String sortOrder,
                                   String tenantDomain) {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.FILTER, filter);
        eventProperties.put(IdentityEventConstants.EventProperty.LIMIT, limit);
        eventProperties.put(IdentityEventConstants.EventProperty.OFFSET, offset);
        eventProperties.put(IdentityEventConstants.EventProperty.SORT_BY, sortBy);
        eventProperties.put(IdentityEventConstants.EventProperty.SORT_ORDER, sortOrder);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.POST_GET_ROLES_EVENT);
        try {
            doPublishEvent(event);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreGetRolesCount(String tenantDomain) {

        try {
            publishPreGetRolesCountWithException(tenantDomain);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreGetRolesCountWithException(String tenantDomain) throws IdentityRoleManagementException {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.PRE_GET_ROLES_COUNT_EVENT);
        doPublishEvent(event);
    }

    public void publishPostGetRolesCount(String tenantDomain) {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.POST_GET_ROLES_COUNT_EVENT);
        try {
            doPublishEvent(event);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreGetRole(String roleID, String tenantDomain) {

        try {
            publishPreGetRoleWithException(roleID, tenantDomain);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreGetRoleWithException(String roleID, String tenantDomain)
            throws IdentityRoleManagementException {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_ID, roleID);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.PRE_GET_ROLE_EVENT);
        doPublishEvent(event);
    }

    public void publishPostGetRole(String roleID, String tenantDomain) {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_ID, roleID);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.POST_GET_ROLE_EVENT);
        try {
            doPublishEvent(event);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreUpdateRoleName(String roleID, String newRoleName, String tenantDomain) {

        try {
            publishPreUpdateRoleNameWithException(roleID, newRoleName, tenantDomain);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreUpdateRoleNameWithException(String roleID, String newRoleName, String tenantDomain)
            throws IdentityRoleManagementException {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_ID, roleID);
        eventProperties.put(IdentityEventConstants.EventProperty.NEW_ROLE_NAME, newRoleName);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.PRE_UPDATE_ROLE_NAME_EVENT);
        doPublishEvent(event);
    }

    public void publishPostUpdateRoleName(String roleID, String newRoleName, String tenantDomain) {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_ID, roleID);
        eventProperties.put(IdentityEventConstants.EventProperty.NEW_ROLE_NAME, newRoleName);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.POST_UPDATE_ROLE_NAME_EVENT);
        try {
            doPublishEvent(event);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreDeleteRole(String roleID, String tenantDomain) {

        try {
            publishPreDeleteRoleWithException(roleID, tenantDomain);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreDeleteRoleWithException(String roleID, String tenantDomain)
            throws IdentityRoleManagementException {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_ID, roleID);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.PRE_DELETE_ROLE_EVENT);
        doPublishEvent(event);
    }

    public void publishPostDeleteRole(String roleID, String tenantDomain) {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_ID, roleID);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.POST_DELETE_ROLE_EVENT);
        try {
            doPublishEvent(event);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreGetUserListOfRole(String roleID, String tenantDomain) {

        try {
            publishPreGetUserListOfRoleWithException(roleID, tenantDomain);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreGetUserListOfRoleWithException(String roleID, String tenantDomain)
            throws IdentityRoleManagementException {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_ID, roleID);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.PRE_GET_USER_LIST_OF_ROLE_EVENT);
        doPublishEvent(event);
    }

    public void publishPostGetUserListOfRole(String roleID, String tenantDomain) {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_ID, roleID);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.POST_GET_USER_LIST_OF_ROLE_EVENT);
        try {
            doPublishEvent(event);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreUpdateUserListOfRole(String roleID, List<String> newUserIDList,
                                                List<String> deletedUserIDList, String tenantDomain) {

        try {
            publishPreUpdateUserListOfRoleWithException(roleID, newUserIDList, deletedUserIDList, tenantDomain);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreUpdateUserListOfRoleWithException(String roleID, List<String> newUserIDList,
                                               List<String> deletedUserIDList, String tenantDomain)
            throws IdentityRoleManagementException {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_ID, roleID);
        eventProperties.put(IdentityEventConstants.EventProperty.NEW_USER_ID_LIST, newUserIDList);
        eventProperties.put(IdentityEventConstants.EventProperty.DELETE_USER_ID_LIST, deletedUserIDList);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.PRE_UPDATE_USER_LIST_OF_ROLE_EVENT);
        doPublishEvent(event);
    }

    public void publishPostUpdateUserListOfRole(String roleID, List<String> newUserIDList,
                                                List<String> deletedUserIDList, String tenantDomain) {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_ID, roleID);
        eventProperties.put(IdentityEventConstants.EventProperty.NEW_USER_ID_LIST, newUserIDList);
        eventProperties.put(IdentityEventConstants.EventProperty.DELETE_USER_ID_LIST, deletedUserIDList);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.POST_UPDATE_USER_LIST_OF_ROLE_EVENT);
        try {
            doPublishEvent(event);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreGetGroupListOfRole(String roleID, String tenantDomain) {

        try {
            publishPreGetGroupListOfRoleWithException(roleID, tenantDomain);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreGetGroupListOfRoleWithException(String roleID, String tenantDomain)
            throws IdentityRoleManagementException {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_ID, roleID);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.PRE_GET_GROUP_LIST_OF_ROLES_EVENT);
        doPublishEvent(event);
    }

    public void publishPostGetGroupListOfRole(String roleID, String tenantDomain) {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_ID, roleID);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.POST_GET_GROUP_LIST_OF_ROLES_EVENT);
        try {
            doPublishEvent(event);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreUpdateGroupListOfRole(String roleID, List<String> newGroupIDList,
                                                List<String> deletedGroupIDList, String tenantDomain) {

        try {
            publishPreUpdateGroupListOfRoleWithException(roleID, newGroupIDList, deletedGroupIDList, tenantDomain);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreUpdateGroupListOfRoleWithException(String roleID, List<String> newGroupIDList,
                                                List<String> deletedGroupIDList, String tenantDomain)
            throws IdentityRoleManagementException {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_ID, roleID);
        eventProperties.put(IdentityEventConstants.EventProperty.NEW_GROUP_ID_LIST, newGroupIDList);
        eventProperties.put(IdentityEventConstants.EventProperty.DELETE_GROUP_ID_LIST, deletedGroupIDList);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.PRE_UPDATE_GROUP_LIST_OF_ROLE_EVENT);
        doPublishEvent(event);
    }

    public void publishPostUpdateGroupListOfRole(String roleID, List<String> newGroupIDList,
                                                List<String> deletedGroupIDList, String tenantDomain) {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_ID, roleID);
        eventProperties.put(IdentityEventConstants.EventProperty.NEW_GROUP_ID_LIST, newGroupIDList);
        eventProperties.put(IdentityEventConstants.EventProperty.DELETE_GROUP_ID_LIST, deletedGroupIDList);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.POST_UPDATE_GROUP_LIST_OF_ROLE_EVENT);
        try {
            doPublishEvent(event);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreGetPermissionListOfRole(String roleID, String tenantDomain) {

        try {
            publishPreGetPermissionListOfRoleWithException(roleID, tenantDomain);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreGetPermissionListOfRoleWithException(String roleID, String tenantDomain)
            throws IdentityRoleManagementException {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_ID, roleID);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.PRE_GET_PERMISSION_LIST_OF_ROLE_EVENT);
        doPublishEvent(event);
    }

    public void publishPostGetPermissionListOfRole(String roleID, String tenantDomain) {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_ID, roleID);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.POST_GET_PERMISSION_LIST_OF_ROLE_EVENT);
        try {
            doPublishEvent(event);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreSetPermissionsForRole(String roleID, List<String> permissions, String tenantDomain) {

        try {
            publishPreSetPermissionsForRoleWithException(roleID, permissions, tenantDomain);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void publishPreSetPermissionsForRoleWithException(String roleID, List<String> permissions,
                                                             String tenantDomain)
            throws IdentityRoleManagementException {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_ID, roleID);
        eventProperties.put(IdentityEventConstants.EventProperty.PERMISSIONS, permissions);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.PRE_SET_PERMISSIONS_FOR_ROLE_EVENT);
        doPublishEvent(event);
    }

    public void publishPostSetPermissionsForRole(String roleID, List<String> permissions, String tenantDomain) {

        Map<String, Object> eventProperties = new HashMap<>();
        eventProperties.put(IdentityEventConstants.EventProperty.ROLE_ID, roleID);
        eventProperties.put(IdentityEventConstants.EventProperty.PERMISSIONS, permissions);
        eventProperties.put(IdentityEventConstants.EventProperty.TENANT_DOMAIN, tenantDomain);
        Event event = createEvent(eventProperties, IdentityEventConstants.Event.POST_SET_PERMISSIONS_FOR_ROLE_EVENT);
        try {
            doPublishEvent(event);
        } catch (IdentityRoleManagementException e) {
            log.error(e.getMessage(), e);
        }
    }

    private Event createEvent(Map<String, Object> eventProperties, String eventName) {

        return new Event(eventName, eventProperties);
    }

    private void doPublishEvent(Event event) throws IdentityRoleManagementException {

        try {
            if (log.isDebugEnabled()) {
                log.debug("Event: " + event.getEventName() + " is published for the role management operation in " +
                        "the tenant with the tenantId: "
                        + event.getEventProperties().get(IdentityEventConstants.EventProperty.TENANT_ID));
            }
            IdentityEventService eventService =
                    RoleManagementServiceComponentHolder.getInstance().getIdentityEventService();
            eventService.handleEvent(event);
        } catch (IdentityEventException e) {
            // Throws an excpetion if the error is a custom event handler error.
            if (RoleConstants.Error.ERROR_CODE_CUSTOM_EVENT_HANDLER_ERROR.getCode().equals(e.getErrorCode())) {
                throw new IdentityRoleManagementException(e.getErrorCode(),
                        "Error while publishing the event: " + event.getEventName() + ".", e);
            }
            log.error("Error while publishing the event: " + event.getEventName() + ".", e);
        }
    }
}
