/**
 * 
 */
package site_mapper.jaxb.menu_items;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Represents the inner element of MenuItem->Type.
 * This will usually be a JsPanel or sub class of it.
 * The strict rules of inheritance are not followed in this case
 * as the sub-classes of this will all be different and this has
 * no state or actions.
 * 
 * ** This was introduced simply to allow multiple 
 * menu item types and attributes. **
 *  
 * TODO - Make into interface?
 */
public abstract class TypeAttributes {
}
