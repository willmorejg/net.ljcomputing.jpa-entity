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
 * Interface shared by all entity services.
 * 
 * @author James G. Willmore
 *
 */
public interface EntityService<T extends PersistedEntity> {
  
  /**
   * Creates the given entity.
   *
   * @param entity the entity
   * @return the long
   */
  T create(T entity);
  
  /**
   * Read all the entities of a given type.
   *
   * @return the list
   */
  List<T> readAll();
  
  /**
   * Read an entity by id.
   *
   * @param id the id
   * @return the t
   */
  T readById(Long id);
  
  /**
   * Update the given entity.
   *
   * @param entity the entity
   * @return TODO
   */
  T update(T entity);
  
  /**
   * Delete the given etity.
   *
   * @param entity the entity
   */
  void delete(T entity);
}
