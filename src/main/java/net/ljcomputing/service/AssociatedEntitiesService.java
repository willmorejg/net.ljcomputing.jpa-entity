/**
           Copyright 2016, James G. Willmore

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package net.ljcomputing.service;

import java.util.List;

import net.ljcomputing.entity.PersistedEntity;

/**
 * Associated entities service. 
 * For example: Used to manipulate membership to a group.
 * 
 * @author James G. Willmore
 *
 */
public interface AssociatedEntitiesService<T extends PersistedEntity, S extends PersistedEntity, R extends PersistedEntity> {
  
  /**
   * Creates the association between the given group and the given member. 
   * For example: add a club member (member) to a club (group).
   *
   * @param group the group
   * @param member the member
   * @return the group member
   */
  R createAssociation(T group, S member);
  
  /**
   * List associated members of a group. 
   * For example: list the club members (members) in a given club (group).
   *
   * @param group the group
   * @return the list of associated members of the group
   */
  List<R> listAssociation(T group);
  
  /**
   * Removes the association between the given group and the given member. 
   * For example: remove a club member (member) from a club (group).
   *
   * @param groupMember the group member
   */
  void removeAssociation(R groupMember);
}
