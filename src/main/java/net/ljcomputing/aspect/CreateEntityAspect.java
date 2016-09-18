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

package net.ljcomputing.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import net.ljcomputing.entity.PersistedEntity;

/**
 * Aspect that modified the entity prior to insert.
 *  Attributes that are modified are the created and
 *  modified time stamps.
 * 
 * @author James G. Willmore
 *
 */
@Aspect
@Component
public class CreateEntityAspect {

  /**
   * Modify the entity prior to creation.
   *
   * @param entity the entity
   */
  @Before("within(net.ljcomputing.repository.BaseCrudRepository+) &&"
      + " execution(* save(..)) && args(entity)")
  public void createEntity(final PersistedEntity entity) {
    createUuid(entity);
    modifiedAt(entity);
  }

  /**
   * Update entity UUID.
   *
   * @param entity the entity
   */
  private void createUuid(final PersistedEntity entity) {
    entity.createUuid();
  }

  /**
   * Update entity modified time stamp.
   *
   * @param entity the entity
   */
  private void modifiedAt(final PersistedEntity entity) {
    entity.modifiedAt();
  }
}
