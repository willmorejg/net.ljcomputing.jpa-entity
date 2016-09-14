/**
           Copyright 2015, James G. Willmore

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

package net.ljcomputing.entity;

import java.io.Serializable;

/**
 * Interface for entity classes. The interface ensures that there will be an id
 * attribute for an entity.
 * 
 * @author James G. Willmore
 *
 */
@SuppressWarnings("PMD.ShortVariable")
public interface PersistedEntity extends Serializable {

  /**
   * Gets the uuid.
   *
   * @return the uuid
   */
  String getUuid();

  /**
   * Sets the uuid.
   *
   * @param uuid the new uuid
   */
  void setUuid(String uuid);

  /**
   * Creates the UUID when needed.
   */
  void createUuid();

  /**
   * Gets the id.
   *
   * @return the id
   */
  Long getId();

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  void setId(Long id);

  /**
   * Gets the created time stamp as a long value.
   *
   * @return the created time stamp as a long value
   */
  Long getCreatedTs();

  /**
   * Gets the modified time stamp.
   *
   * @return the modified time stamp
   */
  Long getModifiedTs();

  /**
   * Sets the created time stamp as a long value.
   *
   * @param createdTs the created time stamp as a long value
   */
  void setCreatedTs(Long createdTs);

  /**
   * Sets the modified time stamp.
   *
   * @param modifiedTs the new modified time stamp
   */
  void setModifiedTs(Long modifiedTs);

  /**
   * Update the modified time stamp with the current date.
   */
  void modifiedAt();
}
